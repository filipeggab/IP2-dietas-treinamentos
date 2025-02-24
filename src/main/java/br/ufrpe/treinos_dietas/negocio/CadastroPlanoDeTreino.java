package br.ufrpe.treinos_dietas.negocio;


import br.ufrpe.treinos_dietas.dados.RepositorioPlanoDeTreino;
import br.ufrpe.treinos_dietas.exceptions.PlanoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumDificuldade;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDoPlano;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreinoPorData;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Treino;

import java.io.Serializable;
import java.time.LocalDate;

public class CadastroPlanoDeTreino implements Serializable {
    private RepositorioPlanoDeTreino repo;

    public void CadastroPlanoDeTreino(){
        this.repo = new RepositorioPlanoDeTreino();
    }

    //overload para os dois tipos de PLano de Treino
    //por data
    public void cadastrarPlanoDeTreino(String nome, EnumDificuldade nivel, EnumObjetivoDoPlano objetivo, LocalDate dataInicial, LocalDate dataFinal){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do plano não pode estar vazio");
        }
        if (nivel == null){
            throw new IllegalArgumentException("O nível do plano não pode ser nulo");
        }
        if (objetivo == null){
            throw new IllegalArgumentException("O objetivo não pode ser nulo");
        }
        if (dataInicial == null){
            throw new IllegalArgumentException("A data inicial não pode ser nula");
        }
        PlanoDeTreino planoDeTreino = new PlanoDeTreinoPorData(nome, objetivo, dataInicial, dataFinal);
        repo.criarPlanoDeTreino(planoDeTreino);
    }


    public void removerPlanoDeTreino(String nome) throws PlanoNaoCadastradoException {
        PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nome);
        repo.apagarPlanoDeTreino(planoDeTreino);
    }

    public PlanoDeTreino lerPlanoDeTreino(String nome){
        try{
            PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nome);
            System.out.println("Nome:" + planoDeTreino.getNome());
            System.out.println("Objetivo:" + planoDeTreino.getObjetivo());
            System.out.println("Data Inicial:" + planoDeTreino.getDataInicial());
            if(planoDeTreino instanceof PlanoDeTreinoPorData){
                System.out.println("Data Final:" + ((PlanoDeTreinoPorData) planoDeTreino).getDataFinal());
            }
            System.out.println("");
            return planoDeTreino;

        } catch (PlanoNaoCadastradoException e){
            System.out.println("Erro:" + e.getMessage());
            return null;
        }
    }


    //para PlanoDeTreinoPorData
    public void editarPlanoDeTreino(String nome, String novoNome, EnumDificuldade novoNivel, EnumObjetivoDoPlano novoObjetivo,
        LocalDate novaDataInicial, LocalDate novaDataFinal) throws PlanoNaoCadastradoException
     {
        PlanoDeTreinoPorData planoDeTreino = (PlanoDeTreinoPorData) repo.retornarPlanoDeTreino(nome);

        if(novoNome != null && !novoNome.trim().isEmpty()){
            planoDeTreino.setNome(novoNome);
        }
        if(novoObjetivo != null){
            planoDeTreino.setObjetivo(novoObjetivo);
        }
        if(novaDataInicial != null){
            planoDeTreino.setDataInicial(novaDataInicial);
        }
        if(novaDataFinal != null){
            planoDeTreino.setDataFinal(novaDataFinal);
        }
    }

    public void adicionarTreino(String nomePlanoTreino, Treino treino) throws PlanoNaoCadastradoException{
        PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nomePlanoTreino);
        planoDeTreino.adicionarTreino(treino);
    }

    public void removerTreino(String nomePlanoTreino, Treino treino) throws PlanoNaoCadastradoException{
        PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nomePlanoTreino);
        planoDeTreino.removerTreino(treino);
    }
}
