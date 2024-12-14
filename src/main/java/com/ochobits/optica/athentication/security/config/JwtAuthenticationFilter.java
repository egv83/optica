package com.ochobits.optica.athentication.security.config;

import com.ochobits.optica.athentication.repository.JpaUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final String jwtSecret;
    private final JpaUserRepository jpaUserRepository;

    public JwtAuthenticationFilter(
            @Value("${security.jwt.key}")
            String jwtSecret, JpaUserRepository jpaUserRepository
    ) {
        this.jwtSecret = jwtSecret;
        this.jpaUserRepository = jpaUserRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        if(Objects.isNull(tokenHeader) || !tokenHeader.startsWith("Bearer ")){
            logger.warn("No hay token de autenticacion");
            filterChain.doFilter(request,response);
            return;
        }

        String jwtToken = tokenHeader.substring(7);
        Claims claims = (Claims) Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        var userName = getSpecificClaims(claims, Claims::getSubject);
        var userOpt = jpaUserRepository.findUserByUserName(userName);
        if(userOpt.isEmpty()){
            logger.error("No existe el usuario propietario del token");
            filterChain.doFilter(request,response);
            return;
        }

//        var authorities = getSpecificClaims(claims, c->
//        {
//            List<String> authoritiesList = c.get("authorities",List.class);
//                if (authoritiesList == null) {
//                    return ""; // Valor predeterminado si no hay "authorities"
//                }
//            return String.join(",",authoritiesList);
//        });
//
//        if (authorities.isEmpty()){
//            logger.error("No hay authorities definidos");
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//        Collection<? extends GrantedAuthority> authorities1 =
//                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

        var user = userOpt.get();
        var userDetails = OpticaUserDetails.builder()
                .userName(user.getUserName())
                .password(null)
                .build();

//        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
//                userDetails,null, authorities1
//        );

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                userDetails,null, userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(upat);
        MDC.put("userName",user.getUserName());
        filterChain.doFilter(request,response);

    }

    private String getSpecificClaims(Claims claims, Function<Claims,String> claimsResolver){
        return claimsResolver.apply(claims);
    }

    private SecretKey getSigninKey(){
        byte[] secretByte = Decoders.BASE64URL.decode(jwtSecret);
        return Keys.hmacShaKeyFor(secretByte);
    }
}
