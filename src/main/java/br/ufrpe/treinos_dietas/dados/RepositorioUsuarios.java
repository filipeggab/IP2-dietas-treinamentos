package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.UsuarioNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "usuarios.dat";
    private static RepositorioUsuarios instancia = new RepositorioUsuarios();

    private List<Usuario> usuarios;
    private static RepositorioUsuarios instance;

    public RepositorioUsuarios() {
        this.usuarios = new ArrayList<>();
        carregarUsuarios(); // Carregar usuários ao iniciar
    }



    public void criarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public void apagarUsuario(Usuario usuario) throws UsuarioNaoCadastradoException {
        if (usuarios.contains(usuario)) {
            usuarios.remove(usuario);
        } else {
            throw new UsuarioNaoCadastradoException(usuario.getEmail());
        }
    }

    public static RepositorioUsuarios getInstance() {
        if (instance == null) {
            instance = new RepositorioUsuarios();
        }
        return instance;
    }

    public void apagarUsuario(String email) throws UsuarioNaoCadastradoException {
        try {
            Usuario user = buscarUsuario(email);
            usuarios.remove(user);
        } catch (UsuarioNaoCadastradoException e) {
            throw e;
        }
    }

    public Usuario buscarUsuario(String email) throws UsuarioNaoCadastradoException {
        Usuario e = usuarios.stream().filter(x -> x.getEmail() == email).findFirst().orElse(null);
        if (e != null) {
            return e;
        } else {
            throw new UsuarioNaoCadastradoException(email);
        }
    }

    public boolean emailUtilizado(String email) {
        for (Usuario eachUsuario : usuarios) {
            if (eachUsuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void salvarUsuarios() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarUsuarios() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                usuarios = (List<Usuario>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading users: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public Usuario getUltimoUsuarioLogado() {
        if (!usuarios.isEmpty()) {
            return usuarios.get(usuarios.size() - 1);
        }
        return null;
    }
}
