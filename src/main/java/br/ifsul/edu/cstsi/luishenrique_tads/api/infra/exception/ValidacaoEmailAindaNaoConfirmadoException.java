package br.ifsul.edu.cstsi.luishenrique_tads.api.infra.exception;

public class ValidacaoEmailAindaNaoConfirmadoException extends RuntimeException {
    public ValidacaoEmailAindaNaoConfirmadoException(String message) {
        super(message);
    }
}
