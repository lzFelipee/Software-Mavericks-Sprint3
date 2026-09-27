package br.com.fiap.fordpublisher.service;

import br.com.fiap.fordpublisher.dto.LoginRequest;
import br.com.fiap.fordpublisher.dto.LoginResponse;
import br.com.fiap.fordpublisher.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.senha()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.username());

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(token);
    }
}