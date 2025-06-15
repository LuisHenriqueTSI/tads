package br.ifsul.edu.cstsi.luishenrique_tads.api.authentication;

public record TokenDTO(String token, String type) {
    public TokenDTO(String token) {
        this(token, "Bearer");
    }
}