package br.ifsul.edu.cstsi.luishenrique_tads.api.ingresso;

public record IngressoDTOPost(
        Integer tipo,  // 1 = Inteira, 2 = Meia
        Long sessaoId  // ID da sessão associada
) {}