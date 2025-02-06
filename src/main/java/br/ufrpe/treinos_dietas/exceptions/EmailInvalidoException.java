package br.ufrpe.treinos_dietas.exceptions;

public class EmailInvalidoException extends Exception{
    public EmailInvalidoException(){
        super("Email já cadastrado.");
    }
}
