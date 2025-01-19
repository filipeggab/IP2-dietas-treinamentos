package Exceptions;

public class SenhaDoUsuarioIncorretaException extends Exception{
    public SenhaDoUsuarioIncorretaException() {
        super("Senha incorreta.");
    }
}