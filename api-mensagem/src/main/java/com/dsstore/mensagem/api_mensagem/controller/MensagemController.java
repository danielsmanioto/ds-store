package com.dsstore.mensagem.api_mensagem.controller;

import com.dsstore.mensagem.api_mensagem.model.Mensagem;
import com.dsstore.mensagem.api_mensagem.repository.MensagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    private final MensagemRepository mensagemRepository;

    public MensagemController(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    @PostMapping
    public Mensagem salvarMensagem(@RequestBody Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    @GetMapping
    public List<Mensagem> buscarTodasMensagens() {
        return mensagemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> buscarMensagemPorId(@PathVariable Long id) {
        Optional<Mensagem> mensagem = mensagemRepository.findById(id);
        return mensagem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

