package com.ds.store.clientes.service;

import com.ds.store.clientes.model.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listarTodos();

    Cliente obterPorId(Long id);

    Cliente adicionar(Cliente cliente);

    Cliente atualizar(Long id, Cliente cliente);

    boolean deletar(Long id);

}
