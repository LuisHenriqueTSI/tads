package br.ifsul.edu.cstsi.luishenrique_tads.api.ingresso;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record IngressoDTOResponse(
        Long id,
        String tipoDescricao,
        LocalDateTime dataCompra,
        SessaoDTO sessao
) {
    public record SessaoDTO(
            Long id,
            String tituloFilme,
            String dataHora,
            BigDecimal valorIngresso
    ) {
        // Pode adicionar métodos auxiliares se necessário
    }

    // Método estático de construção (opcional)
    public static IngressoDTOResponse fromIngresso(Ingresso ingresso) {
        return new IngressoDTOResponse(
                ingresso.getId(),
                ingresso.getTipoDescricao(),
                ingresso.getDataCompra(),
                new SessaoDTO(
                        ingresso.getSessao().getId(),
                        ingresso.getSessao().getFilme().getTitulo(),
                        formatarDataHora(ingresso.getSessao().getDtSessao(), ingresso.getSessao().getHorSessao()),
                        calcularValor(ingresso)
                )
        );
    }

    private static String formatarDataHora(Object data, Object hora) {
        return (data != null && hora != null)
                ? data.toString() + " " + hora.toString()
                : "Data/hora não disponível";
    }

    private static BigDecimal calcularValor(Ingresso ingresso) {
        return ingresso.getTipo() == 1
                ? ingresso.getSessao().getValorInteira()
                : ingresso.getSessao().getValorMeia();
    }
}