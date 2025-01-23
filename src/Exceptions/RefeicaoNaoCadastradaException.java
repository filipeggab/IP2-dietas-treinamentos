package Exceptions;

public class RefeicaoNaoCadastradaException extends Exception{
    public RefeicaoNaoCadastradaException(String nome){
        super("A refeição "+nome+" não está cadastrada.");
    }
}
