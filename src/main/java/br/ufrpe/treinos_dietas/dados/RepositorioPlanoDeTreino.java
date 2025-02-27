package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.PlanoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPlanoDeTreino {
    private static RepositorioPlanoDeTreino instancia;
    private List<PlanoDeTreino> planos;

    public RepositorioPlanoDeTreino() {
        this.planos = new ArrayList<>();
    }

    public static RepositorioPlanoDeTreino getInstance() {
        if (instancia == null) {
            instancia = new RepositorioPlanoDeTreino();
        }
        return instancia;
    }

    public boolean planoDeTreinoExiste(String nome){
        for (PlanoDeTreino p : planos) {
            if (p.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void criarPlanoDeTreino(PlanoDeTreino planoDeTreino) {
        if (planoDeTreino == null) {
            throw new IllegalArgumentException("O plano não pode ser nulo.");
        }
        planos.add(planoDeTreino);
        System.out.println("Plano adicionado: " + planoDeTreino.getNome());
    }
    public void apagarPlanoDeTreino(PlanoDeTreino planoDeTreino) throws PlanoNaoCadastradoException {
        if(planos.contains(planoDeTreino)){
            planos.remove(planoDeTreino);
        }
        else{
            throw new PlanoNaoCadastradoException(planoDeTreino.getNome());
        }
    }
    public PlanoDeTreino retornarPlanoDeTreino(String nome) throws PlanoNaoCadastradoException {
        if (nome == null) {
            throw new IllegalArgumentException("O nome do plano não pode ser nulo.");
        }
        return planos.stream()
                .filter(x -> nome.trim().equalsIgnoreCase(x.getNome().trim()))
                .findFirst()
                .orElseThrow(() -> new PlanoNaoCadastradoException(nome));
    }

    public String retornarNomeDoPlanoDeTreino(){
        return planos.getLast().getNome();
    }
    public PlanoDeTreino retornarPlanos(){
        return planos.getLast();
    }

    public List<PlanoDeTreino> getPlanos(){
        return this.planos;
    }
}