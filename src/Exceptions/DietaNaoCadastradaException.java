package Exceptions;

public class DietaNaoCadastradaException extends Exception {
    public DietaNaoCadastradaException(String nome){
        super("Dieta "+nome+" não existe.");
    }
}
