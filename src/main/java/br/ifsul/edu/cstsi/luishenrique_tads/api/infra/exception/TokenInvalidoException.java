package br.ifsul.edu.cstsi.luishenrique_tads.api.infra.exception;


public class TokenInvalidoException extends RuntimeException{
    public TokenInvalidoException(String mensagem) {
        super(mensagem);
    }
}
