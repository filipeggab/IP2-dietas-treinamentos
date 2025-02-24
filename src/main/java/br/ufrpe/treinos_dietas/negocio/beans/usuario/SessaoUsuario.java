package br.ufrpe.treinos_dietas.negocio.beans.usuario;

import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessaoUsuario implements Serializable {
    private static SessaoUsuario instancia;
    private Usuario usuario;
    private List<Metrica> metricas;
    private List<PlanoDeTreino> planoDeTreinoList;
    private List<Dieta> dietaList;

    private SessaoUsuario() {
        this.usuario = new Usuario();
        this.metricas = new ArrayList<>();
        this.planoDeTreinoList = new ArrayList<>();
        this.dietaList = new ArrayList<>();
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
        this.metricas.clear();
        this.planoDeTreinoList.clear();
        this.dietaList.clear();
    }
}

