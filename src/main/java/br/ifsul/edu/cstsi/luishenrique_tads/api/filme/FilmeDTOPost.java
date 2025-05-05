package br.ifsul.edu.cstsi.luishenrique_tads.api.filme;

import java.time.Duration;

public record FilmeDTOPost(
        String titulo,
        Duration duracao,
        String genero,
        String classificacao,
        String diretor
) {
    public FilmeDTOPost {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (duracao == null || duracao.isNegative() || duracao.isZero()) {
            throw new IllegalArgumentException("Duração inválida");
        }
    }
}