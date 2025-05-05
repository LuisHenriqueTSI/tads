package br.ifsul.edu.cstsi.luishenrique_tads.api.sala;

public record SalaDTOResponse(
        Long id,
        Integer numero,
        Integer capacidade,
        String descricao
) {}