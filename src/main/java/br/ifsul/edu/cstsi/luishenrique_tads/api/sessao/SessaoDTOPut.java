package br.ifsul.edu.cstsi.luishenrique_tads.api.sessao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record SessaoDTOPut(
        LocalDate dtSessao,
        LocalTime horSessao,
        BigDecimal valorInteira,
        BigDecimal valorMeia
) {
    public SessaoDTOPut {
        if (dtSessao == null && horSessao == null &&
                valorInteira == null && valorMeia == null) {
            throw new IllegalArgumentException("Pelo menos um campo deve ser informado");
        }
    }
}