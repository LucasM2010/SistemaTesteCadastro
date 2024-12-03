package com.example.service;

import com.example.Outros.Cliente;
import com.example.repository.ClienteRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Remover a anotação @Autowired, pois ela é desnecessária com um único construtor
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não pode ser nulo");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(int id, Cliente clienteAtualizado) {
        if (clienteAtualizado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente atualizado não pode ser nulo");
        }

        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com ID " + id);
        }

        clienteAtualizado.setId(id);
        return clienteRepository.save(clienteAtualizado);
    }

    public void excluirCliente(int id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado com ID " + id);
        }
        clienteRepository.deleteById(id);
    }
}
