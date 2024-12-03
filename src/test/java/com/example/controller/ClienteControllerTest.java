package com.example.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Outros.Cliente;
import com.example.service.ClienteService;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void testCriarCliente() throws Exception {
        Cliente cliente = new Cliente(1, "Lucas", "lucas@email.com", "123456789", "Rua A");
        when(clienteService.adicionarCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                .contentType("application/json")
                .content("{\"nome\":\"Lucas\",\"email\":\"lucas@email.com\",\"telefone\":\"123456789\",\"endereco\":\"Rua A\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Lucas"))
                .andExpect(jsonPath("$.email").value("lucas@email.com"));

        verify(clienteService, times(1)).adicionarCliente(any(Cliente.class));
    }
}
