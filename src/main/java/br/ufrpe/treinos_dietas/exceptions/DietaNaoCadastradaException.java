package br.ufrpe.treinos_dietas.exceptions;

public class DietaNaoCadastradaException extends Exception {
    public DietaNaoCadastradaException(String nome){
        super("Dieta "+nome+" não existe.");
    }
}
