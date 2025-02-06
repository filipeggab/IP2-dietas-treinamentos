package br.ufrpe.treinos_dietas.exceptions;

public class ExercicioJaExisteException extends Exception{
    public ExercicioJaExisteException() {
        super("Exercicio já existe.");
    }
}
