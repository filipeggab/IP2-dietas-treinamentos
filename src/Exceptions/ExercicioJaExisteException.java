package Exceptions;

public class ExercicioJaExisteException extends Exception{
    public ExercicioJaExisteException() {
        super("Exercicio já existe.");
    }
}
