package com.ds.store.clientes.service;

import com.ds.store.clientes.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteService implements IClienteService {
    private List<Cliente> clientes = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Cliente> listarTodos() {
        return clientes;
    }

    @Override
    public Cliente obterPorId(Long id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Cliente adicionar(Cliente cliente) {
        cliente.setId(nextId++);
        clientes.add(cliente);
        return cliente;
    }

    @Override
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            return cliente;
        }
        return null;
    }

    @Override
    public boolean deletar(Long id) {
        return clientes.removeIf(cliente -> cliente.getId().equals(id));
    }
}
