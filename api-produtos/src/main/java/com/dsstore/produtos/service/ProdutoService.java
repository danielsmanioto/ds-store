package com.dsstore.produtos.service;

import com.dsstore.produtos.model.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> listarTodos();
    Produto obterPorId(Long id);
    Produto adicionar(Produto produto);
    Produto atualizar(Long id, Produto produto);
    boolean deletar(Long id);
}

