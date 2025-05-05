package br.ifsul.edu.cstsi.luishenrique_tads.api.filme;

import java.time.Duration;

public record FilmeDTOPut(
        String titulo,
        Duration duracao,
        String genero,
        String classificacao,
        String diretor
) {
    public FilmeDTOPut {
        if (titulo != null && titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        if (duracao != null && (duracao.isNegative() || duracao.isZero())) {
            throw new IllegalArgumentException("Duração inválida");
        }
    }
}