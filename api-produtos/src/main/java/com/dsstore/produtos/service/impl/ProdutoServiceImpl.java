package com.dsstore.produtos.service.impl;

import com.dsstore.produtos.model.Produto;
import com.dsstore.produtos.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private List<Produto> produtos = new ArrayList<>();
    private Long contadorId = 1L;

    @Override
    public List<Produto> listarTodos() {
        return produtos;
    }

    @Override
    public Produto obterPorId(Long id) {
        Optional<Produto> produto = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
        return produto.orElse(null);
    }

    @Override
    public Produto adicionar(Produto produto) {
        produto.setId(contadorId++);
        produtos.add(produto);
        return produto;
    }

    @Override
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = obterPorId(id);
        if (produto != null) {
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            return produto;
        }
        return null;
    }

    @Override
    public boolean deletar(Long id) {
        return produtos.removeIf(p -> p.getId().equals(id));
    }
}

