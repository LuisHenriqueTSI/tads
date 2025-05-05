package br.ifsul.edu.cstsi.luishenrique_tads.api.sala;

public record SalaDTOPut(
        Integer numero,      // Número da sala (opcional para atualização)
        Integer capacidade,  // Nova capacidade (opcional)
        String descricao    // Nova descrição (opcional)
) {
    // Validação manual no construtor compacto
    public SalaDTOPut {
        if (numero == null && capacidade == null && descricao == null) {
            throw new RuntimeException("Pelo menos um campo deve ser informado para atualização");
        }
        if (capacidade != null && capacidade <= 0) {
            throw new RuntimeException("Capacidade deve ser maior que zero");
        }
    }
}