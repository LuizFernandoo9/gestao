package com.fernando.gestao.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já Existe!");
    }
}
