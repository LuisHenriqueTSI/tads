package br.ifsul.edu.cstsi.luishenrique_tads.api.filme;

import jakarta.validation.constraints.*;
import java.time.Duration;

public record FilmeDTOPost(
        @NotBlank(message = "Título é obrigatório")
        @Size(min = 2, max = 100, message = "Título deve ter entre 2 e 100 caracteres")
        String titulo,

        @NotNull(message = "Duração é obrigatória")
        Duration duracao,

        @Size(max = 50, message = "Gênero deve ter no máximo 50 caracteres")
        String genero,

        @Size(max = 20, message = "Classificação deve ter no máximo 20 caracteres")
        String classificacao,

        @Size(max = 100, message = "Diretor deve ter no máximo 100 caracteres")
        String diretor
) {
    public FilmeDTOPost {
        // Validações programáticas (mantendo suas regras atuais)
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (duracao == null || duracao.isNegative() || duracao.isZero()) {
            throw new IllegalArgumentException("Duração deve ser positiva");
        }

        // Validações adicionais baseadas nas anotações
        if (titulo.length() < 2 || titulo.length() > 100) {
            throw new IllegalArgumentException("Título deve ter entre 2 e 100 caracteres");
        }
        if (genero != null && genero.length() > 50) {
            throw new IllegalArgumentException("Gênero deve ter no máximo 50 caracteres");
        }
        if (classificacao != null && classificacao.length() > 20) {
            throw new IllegalArgumentException("Classificação deve ter no máximo 20 caracteres");
        }
        if (diretor != null && diretor.length() > 100) {
            throw new IllegalArgumentException("Diretor deve ter no máximo 100 caracteres");
        }
    }
}