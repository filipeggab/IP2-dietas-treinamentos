package Exceptions;

public class UsuarioJaCadastradoException extends Exception{
    public UsuarioJaCadastradoException(String email) {
        super("Usuário do email "+ email + "já cadastrado!");

    }
}
