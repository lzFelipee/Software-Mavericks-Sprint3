package br.com.fiap.fordpublisher;

import br.com.fiap.fordpublisher.model.Usuario;
import br.com.fiap.fordpublisher.repository.UsuarioRepository;
import br.com.fiap.fordpublisher.security.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (usuarioRepository.findByUsername("admin").isEmpty()) {

                Usuario admin = new Usuario();

                admin.setUsername("admin");
                admin.setSenha(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);

                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("user").isEmpty()) {

                Usuario user = new Usuario();

                user.setUsername("user");
                user.setSenha(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);

                usuarioRepository.save(user);
            }
        };
    }
}