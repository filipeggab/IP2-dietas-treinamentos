package dados;

import Exceptions.SenhaDoUsuarioIncorretaException;
import negocio.beans.usuario.Usuario;
import Exceptions.UsuarioNaoCadastradoException;

import java.util.List;

public class RepositorioUsuarios {
    private List<Usuario> usuarios;

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public void removerUsuario(Usuario usuario) throws UsuarioNaoCadastradoException{
        if(usuarios.contains(usuario)){
            usuarios.remove(usuario);
        }else{
            throw new UsuarioNaoCadastradoException(usuario.getEmail());
        }
    }
    public Usuario buscarUsuario(String email) throws UsuarioNaoCadastradoException{
        Usuario e = usuarios.stream().filter(x -> x.getEmail() == email).findFirst().orElse(null);
        if(e != null){
            return e;
        }else{
            throw new UsuarioNaoCadastradoException(email);
        }
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void alterarEmail(String emailDaConta, String novoEmail) throws UsuarioNaoCadastradoException{
        try {
            Usuario user = buscarUsuario(emailDaConta);
            user.setEmail(novoEmail);
        } catch (UsuarioNaoCadastradoException e) {
            throw new UsuarioNaoCadastradoException(emailDaConta);
        }
    }
    public void alterarSenha(String emailDaConta, String senhaDaConta, String novaSenha) throws UsuarioNaoCadastradoException, SenhaDoUsuarioIncorretaException{
        try{
            Usuario user = buscarUsuario(emailDaConta);
            if (user.getSenha() == senhaDaConta){
                user.setSenha(novaSenha);
            }
            else{
                throw new SenhaDoUsuarioIncorretaException();
            }
        } catch (UsuarioNaoCadastradoException e) {
            throw new UsuarioNaoCadastradoException(emailDaConta);
        }
    }
}
