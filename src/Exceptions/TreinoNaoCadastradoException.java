package Exceptions;

public class TreinoNaoCadastradoException extends Exception{
    public TreinoNaoCadastradoException(String nome){
        super("O treino "+nome+" n" +" não está cadastrado!");
    }
}
