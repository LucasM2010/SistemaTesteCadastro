package com.example.service;

import com.example.Outros.Cliente;
import com.example.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // Certifique-se de que o MockitoExtension está sendo utilizado
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void testAdicionarCliente() {
        // Arrange
        Cliente cliente = new Cliente(1, "Lucas", "lucas@email.com", "123456789", "Rua A");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Act
        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);

        // Assert
        assertNotNull(clienteSalvo);
        assertEquals("Lucas", clienteSalvo.getNome());
        verify(clienteRepository, times(1)).save(cliente);  // Verifica se o método save foi chamado uma vez
    }
}
