package com.example.api_notifica_consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KafkaConsumer {

    private final WebClient webClient;

    public KafkaConsumer(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8085").build();
    }

    @KafkaListener(topics = "my-topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Mensagem recebida: " + message);

        salvarMensagem(message);
    }

    private void salvarMensagem(String conteudo) {
        Mensagem mensagem = new Mensagem(conteudo);

        webClient.post()
                .uri("/mensagens")
                .body(Mono.just(mensagem), Mensagem.class)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> System.out.println("Resposta da API de mensagens: " + response),
                        error -> System.err.println("Erro ao salvar mensagem: " + error.getMessage()));
    }

    static class Mensagem {
        private String conteudo;

        public Mensagem(String conteudo) {
            this.conteudo = conteudo;
        }

        public String getConteudo() {
            return conteudo;
        }

        public void setConteudo(String conteudo) {
            this.conteudo = conteudo;
        }
    }
}
