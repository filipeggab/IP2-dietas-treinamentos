package Exceptions;

public class UsuarioNaoCadastradoException extends Exception{
    public UsuarioNaoCadastradoException(String email) {
        super("Usuário do email "+ email + "não está cadastrado!");
    }
}
