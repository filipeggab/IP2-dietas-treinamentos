package br.ufrpe.treinos_dietas.exceptions;

public class ExercicioNaoCadastradoException extends Exception{
    public ExercicioNaoCadastradoException(String nome){
        super("O exercício "+nome+" não existe!");
    }
}
