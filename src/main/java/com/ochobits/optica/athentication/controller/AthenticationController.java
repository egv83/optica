package com.ochobits.optica.athentication.controller;

import com.ochobits.optica.athentication.dto.*;
import com.ochobits.optica.athentication.entities.UserEntity;
import com.ochobits.optica.athentication.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AthenticationController {

    private final AuthenticationService authenticationService;

    public AthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/v1/getUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){

        List<UserResponseDto> response = authenticationService.getAllUsers().stream()
                .map(
                        userEntityStream -> new UserResponseDto(
                                userEntityStream.getId(),
                                userEntityStream.getUserName(),
                                userEntityStream.getPassword()
                        )
                ).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = null;
        LoginRequest loginRequest = new LoginRequest(
                request.userName(),
                request.password()
        );

        LoginResponse result = authenticationService.login(loginRequest);
        response = new LoginResponse(result.userName(),result.token(),result.message());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/register")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request){
        SignUpResponse response = null;
        SignUpRequest signUp = new SignUpRequest(
                request.userName(),
                request.password()
        );

        SignUpResponse result = authenticationService.signUp(request);
        response = new SignUpResponse(result.message());
        return  ResponseEntity.ok(response);
    }

}
