package br.ufrpe.treinos_dietas.exceptions;

public class FalhaDeAuthException extends Exception{
    public FalhaDeAuthException(){
        super("Senha não compatível com a da conta.");
    }
}
