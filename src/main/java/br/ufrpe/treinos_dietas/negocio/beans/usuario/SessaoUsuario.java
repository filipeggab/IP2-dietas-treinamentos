package br.ufrpe.treinos_dietas.negocio.beans.usuario;

public class SessaoUsuario {
    private static SessaoUsuario instancia;
    private Usuario usuario;

    private SessaoUsuario() {
        this.usuario = new Usuario();
    }

    public static SessaoUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SessaoUsuario();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void limparSessao() {
        this.usuario = new Usuario();
    }
}

