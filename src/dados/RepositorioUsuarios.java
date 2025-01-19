package dados;

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
}
