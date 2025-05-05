package br.ifsul.edu.cstsi.luishenrique_tads.api.sessao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record SessaoDTOPost(
        LocalDate dtSessao,
        LocalTime horSessao,
        BigDecimal valorInteira,
        BigDecimal valorMeia,
        Long filmeId
) {
    public SessaoDTOPost {
        if (dtSessao == null || horSessao == null) {
            throw new IllegalArgumentException("Data e horário são obrigatórios");
        }
        if (valorInteira == null || valorMeia == null) {
            throw new IllegalArgumentException("Valores são obrigatórios");
        }
        if (filmeId == null) {
            throw new IllegalArgumentException("Filme é obrigatório");
        }
    }
}