package com.dam.proyectospring;

import com.dam.proyectospring.modelos.Piloto;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PilotoClient {
    private final WebClient client;

    public PilotoClient(String baseUrl) {
        this.client = WebClient.create(baseUrl);
    }

    public Flux<Piloto> getAllPilotos() {
        return client.get()
                .uri("/pilotos")
                .retrieve()
                .bodyToFlux(Piloto.class);
    }

    public Mono<Piloto> getPilotoById(Long id) {
        return client.get()
                .uri("/piloto/{id}", id)
                .retrieve()
                .bodyToMono(Piloto.class);
    }

    public Mono<Piloto> addPiloto(Piloto nuevoPiloto) {
        return client.post()
                .uri("/pilotos")
                .body(Mono.just(nuevoPiloto), Piloto.class)
                .retrieve()
                .bodyToMono(Piloto.class);
    }

    public Mono<Piloto> modifyPiloto(Long id, Piloto pilotoActualizado) {
        return client.put()
                .uri("/pilotos/{id}", id)
                .body(Mono.just(pilotoActualizado), Piloto.class)
                .retrieve()
                .bodyToMono(Piloto.class);
    }

    public Mono<Void> deletePiloto(Long id) {
        return client.delete()
                .uri("/pilotos/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
