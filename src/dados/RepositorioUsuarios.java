package dados;

import Exceptions.SenhaDoUsuarioIncorretaException;
import negocio.beans.usuario.Usuario;
import Exceptions.UsuarioNaoCadastradoException;

import java.util.List;

public class RepositorioUsuarios {
    private List<Usuario> usuarios;

    public void criarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public void apagarUsuario(Usuario usuario) throws UsuarioNaoCadastradoException{
        if(usuarios.contains(usuario)){
            usuarios.remove(usuario);
        }else{
            throw new UsuarioNaoCadastradoException(usuario.getEmail());
        }
    }
    public void apagarUsuario(String email) throws UsuarioNaoCadastradoException{
        try{
            Usuario user = buscarUsuario(email);
            usuarios.remove(user);
        } catch (UsuarioNaoCadastradoException e) {
            throw e;
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
    public boolean emailUtilizado(String email){
        for(Usuario eachUsuario : usuarios){
            if(eachUsuario.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
