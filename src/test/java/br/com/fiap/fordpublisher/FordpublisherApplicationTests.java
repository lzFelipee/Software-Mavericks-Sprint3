package br.com.fiap.fordpublisher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FordpublisherApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextoDeveCarregar() {
        // Verifica se a aplicação consegue iniciar corretamente.
    }

    @Test
    void loginDeveRetornarToken() throws Exception {

        String loginJson = """
                {
                    "username": "admin",
                    "senha": "admin123"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk());
    }

    @Test
    void endpointProtegidoSemTokenDeveRetornar403() throws Exception {

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isForbidden());
    }

    @Test
    void userDeveConseguirConsultarClientes() throws Exception {

        String loginJson = """
                {
                    "username": "user",
                    "senha": "user123"
                }
                """;

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = extrairToken(response);

        mockMvc.perform(get("/clientes")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void userNaoDeveConseguirCriarCliente() throws Exception {

        String loginJson = """
                {
                    "username": "user",
                    "senha": "user123"
                }
                """;

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = extrairToken(response);

        String clienteJson = """
                {
                    "nome": "Cliente Teste",
                    "email": "teste@teste.com",
                    "telefone": "11999999999"
                }
                """;

        mockMvc.perform(post("/clientes")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminDeveConseguirCriarCliente() throws Exception {

        String loginJson = """
                {
                    "username": "admin",
                    "senha": "admin123"
                }
                """;

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = extrairToken(response);

        String clienteJson = """
                {
                    "nome": "Cliente Admin Teste",
                    "email": "admin@teste.com",
                    "telefone": "11999999999"
                }
                """;

        mockMvc.perform(post("/clientes")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isCreated());
    }

	@Test
void clienteInexistenteDeveRetornar404() throws Exception {

    String loginJson = """
            {
                "username": "admin",
                "senha": "admin123"
            }
            """;

    String response = mockMvc.perform(post("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(loginJson))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    String token = extrairToken(response);

    mockMvc.perform(get("/clientes/999999")
                    .header("Authorization", "Bearer " + token))
            .andExpect(status().isNotFound());
}

    private String extrairToken(String response) {
        return response
                .replace("{", "")
                .replace("}", "")
                .replace("\"token\":", "")
                .replace("\"", "")
                .trim();
    }
}