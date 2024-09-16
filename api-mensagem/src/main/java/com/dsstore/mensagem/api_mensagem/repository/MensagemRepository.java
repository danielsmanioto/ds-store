package com.dsstore.mensagem.api_mensagem.repository;

import com.dsstore.mensagem.api_mensagem.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
