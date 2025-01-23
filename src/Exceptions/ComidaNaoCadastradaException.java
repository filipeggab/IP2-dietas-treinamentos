package Exceptions;

public class ComidaNaoCadastradaException extends Exception{
    public ComidaNaoCadastradaException(String nome){
        super(nome+" não está cadastrado!");
    }
}
