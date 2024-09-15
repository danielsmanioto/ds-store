package com.ds.store.clientes.demo.controller;

import com.ds.store.clientes.demo.model.Cliente;
import com.ds.store.clientes.demo.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obterPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.adicionar(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizar(id, cliente);
        if (clienteAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        boolean deletado = clienteService.deletar(id);
        if (!deletado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
