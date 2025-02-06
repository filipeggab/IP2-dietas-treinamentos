package br.ufrpe.treinos_dietas.exceptions;

public class ComidaNaoCadastradaException extends Exception{
    public ComidaNaoCadastradaException(String nome){
        super(nome+" não está cadastrado!");
    }
}
