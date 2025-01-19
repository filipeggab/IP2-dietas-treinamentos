package Exceptions;

public class PlanoNaoCadastradoException extends Exception{
    public PlanoNaoCadastradoException(String nomeDoPlano) {
        super("O plano "+ nomeDoPlano + " não está cadastrado!");
    }
}
