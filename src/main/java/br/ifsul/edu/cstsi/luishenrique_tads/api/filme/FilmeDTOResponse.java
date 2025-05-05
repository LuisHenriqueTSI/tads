package br.ifsul.edu.cstsi.luishenrique_tads.api.filme;

import java.time.Duration;
import java.time.LocalDateTime;

public record FilmeDTOResponse(
        Long id,
        String titulo,
        String duracaoFormatada,
        String genero,
        String classificacao,
        String diretor,
        LocalDateTime dataCriacao,
        int totalSessoes
) {
    public static String formatDuration(Duration duration) {
        return String.format("%dh %02dm",
                duration.toHours(),
                duration.toMinutesPart());
    }
}