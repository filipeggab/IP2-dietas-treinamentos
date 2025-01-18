package Exceptions;

public class UsuarioJaCadastradoException extends Exception{
    public UsuarioJaCadastradoException() {
        super("Usuário já cadastrado!");

    }
}
