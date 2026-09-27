package br.com.fiap.fordpublisher.controller;

import br.com.fiap.fordpublisher.dto.LoginRequest;
import br.com.fiap.fordpublisher.dto.LoginResponse;
import br.com.fiap.fordpublisher.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}