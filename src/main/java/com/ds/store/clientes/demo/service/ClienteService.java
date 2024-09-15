package com.ds.store.clientes.demo.service;

import com.ds.store.clientes.demo.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClienteService {

    List<Cliente> listarTodos();

    Cliente obterPorId(Long id);

    Cliente adicionar(Cliente cliente);

    Cliente atualizar(Long id, Cliente cliente);

    boolean deletar(Long id);

}
