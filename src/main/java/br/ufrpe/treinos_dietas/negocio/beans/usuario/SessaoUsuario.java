package br.ufrpe.treinos_dietas.negocio.beans.usuario;

import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;

import java.util.ArrayList;
import java.util.List;

public class SessaoUsuario {
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
    public List<Metrica> getMetricas() {
        return metricas;
    }
    public List<PlanoDeTreino> getPlanoDeTreinoList() {
        return planoDeTreinoList;
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

