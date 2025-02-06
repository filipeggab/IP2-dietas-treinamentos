package br.ufrpe.treinos_dietas.exceptions;

public class UsuarioNaoCadastradoException extends Exception{
    public UsuarioNaoCadastradoException(String email) {
        super("Usuário do email "+ email + "não está cadastrado!");
    }
}
