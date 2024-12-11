package com.ochobits.optica.athentication.services;

import com.ochobits.optica.Utils.GenericIdGenerator;
import com.ochobits.optica.athentication.dto.LoginRequest;
import com.ochobits.optica.athentication.dto.LoginResponse;
import com.ochobits.optica.athentication.dto.SignUpRequest;
import com.ochobits.optica.athentication.dto.SignUpResponse;
import com.ochobits.optica.athentication.entities.UserEntity;
import com.ochobits.optica.athentication.models.User;
import com.ochobits.optica.athentication.repository.JpaUserRepository;
import com.ochobits.optica.athentication.security.interfaces.PasswordEncoder;
import com.ochobits.optica.athentication.security.interfaces.TokenGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    public AuthenticationService(JpaUserRepository jpaUserRepository, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
        this.jpaUserRepository = jpaUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public LoginResponse login(LoginRequest loginRequest){
        try{
            var userOpt = jpaUserRepository.findUserByUserName(loginRequest.userName());
            if(userOpt.isEmpty()){
                throw new RuntimeException("El usuario no existe");
            }

            var user = User.builder()
                    .id(userOpt.get().getId())
                    .userName(userOpt.get().getUserName())
                    .password(userOpt.get().getPassword())
                    .build();

            if(!passwordEncoder.matches(loginRequest.password(),user.getPassword())){
                throw new RuntimeException("Clave incorrecta");
            }

            var token = tokenGenerator.generate(user);
            return  new LoginResponse(user.getUserName(),token,"OK");

        }catch (RuntimeException e){
            return new LoginResponse(loginRequest.userName(),"",e.getMessage());
        }
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest){
        try{
            if(signUpRequest.userName().isBlank()){
                throw new RuntimeException("No se permite el usuario vacio");
            }

            if(signUpRequest.password().isBlank()){
                throw new RuntimeException("No se permite el password vacio");
            }

            var userOpt = jpaUserRepository.findUserByUserName(signUpRequest.userName());
            if(userOpt.isPresent()){
                throw new RuntimeException("El usuario ya existe");
            }

            var user = UserEntity.builder()
                    .id(GenericIdGenerator.getNewId(jpaUserRepository))
                    .userName(signUpRequest.userName())
                    .password(passwordEncoder.encode(signUpRequest.password()))
                    .build();

            jpaUserRepository.save(user);
            return new SignUpResponse("Usuario creado");
        }catch (RuntimeException e){
            return new SignUpResponse(e.getMessage());
        }
    }

    public List<UserEntity> getAllUsers(){
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        return userEntities;
    }

}
