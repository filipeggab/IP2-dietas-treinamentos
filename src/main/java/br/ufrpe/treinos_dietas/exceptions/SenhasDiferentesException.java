package br.ufrpe.treinos_dietas.exceptions;

public class SenhasDiferentesException extends Exception {
    public SenhasDiferentesException() {
        super("As senhas não são iguais!");
    }
}
