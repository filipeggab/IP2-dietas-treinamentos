package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.PlanoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumDificuldade;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDoPlano;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreinoPorData;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreinoPorQuantidade;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPlanoDeTreino {
    private List<PlanoDeTreino> planos;

    public RepositorioPlanoDeTreino() {
        this.planos = new ArrayList<>();
    }

    public void criarPlanoDeTreino(PlanoDeTreino planoDeTreino) {
        planos.add(planoDeTreino);
    }
    public void apagarPlanoDeTreino(PlanoDeTreino planoDeTreino) throws PlanoNaoCadastradoException {
        if(planos.contains(planoDeTreino)){
            planos.remove(planoDeTreino);
        }
        else{
            throw new PlanoNaoCadastradoException(planoDeTreino.getNome());
        }
    }
    public PlanoDeTreino retornarPlanoDeTreino(String nome) throws PlanoNaoCadastradoException{
        PlanoDeTreino plano = planos.stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
        if(plano == null){
            throw new PlanoNaoCadastradoException(nome);
        }else{
            return plano;
        }
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorData() {
        List<PlanoDeTreino> treinosPorData = planos.stream().filter(x -> x instanceof PlanoDeTreinoPorData).toList();
        return treinosPorData;
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorQuantidade() {
        List<PlanoDeTreino> treinosPorQuantidade = planos.stream().filter(x -> x instanceof PlanoDeTreinoPorQuantidade).toList();
        return treinosPorQuantidade;
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorObjetivo(EnumObjetivoDoPlano obj){
        return planos.stream().filter(x -> x.getObjetivo() == obj).toList();
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorDificuldade(EnumDificuldade diff){
        return planos.stream().filter(x -> x.getNivel().equals(diff)).toList();
    }
    public List<PlanoDeTreino> getPlanos(){
        return this.planos;
    }
}