package br.ufrpe.treinos_dietas.exceptions;

public class SenhaDoUsuarioIncorretaException extends Exception{
    public SenhaDoUsuarioIncorretaException() {
        super("Senha incorreta.");
    }
}