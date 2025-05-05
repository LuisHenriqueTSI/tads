package br.ifsul.edu.cstsi.luishenrique_tads.api.sessao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SessaoDTOResponse(
        Long id,
        String dataHora,
        BigDecimal valorInteira,
        BigDecimal valorMeia,
        FilmeInfo filme
) {
    public record FilmeInfo(
            Long id,
            String titulo
    ) {}
}