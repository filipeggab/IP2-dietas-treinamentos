package br.ufrpe.treinos_dietas.negocio;


import br.ufrpe.treinos_dietas.dados.RepositorioTreinos;
import br.ufrpe.treinos_dietas.exceptions.TreinoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDeExercicio;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.ExercicioPratico;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Treino;

import java.util.List;

public class CadastroTreinos {
    private RepositorioTreinos repo;

    public void CadastroTreinos(){
        this.repo = new RepositorioTreinos();
    }

    public void cadastrarTreino(String nome, List<EnumObjetivoDeExercicio> foco){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do Treino não pode estar vazio.");
        }
        if (foco == null || foco.isEmpty()){
            throw new IllegalArgumentException("O foco do treino não pode estar vazio.");
        }

        Treino novoTreino = new Treino(nome, foco);
        repo.criarTreino(novoTreino);
    }

    public void cadastrarTreino(String nome, List<EnumObjetivoDeExercicio> foco, List<ExercicioPratico> exercicioList){
        Treino novoTreino;

        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do Treino não pode estar vazio.");
        }
        if (foco == null || foco.isEmpty()){
            throw new IllegalArgumentException("O foco do treino não pode estar vazio.");
        }
        if (exercicioList != null && !exercicioList.isEmpty()){
            novoTreino = new Treino(nome, foco, exercicioList);
        } else{
            novoTreino = new Treino(nome, foco);
        }

        repo.criarTreino(novoTreino);
    }  

    public void RemoverTreino(String nome) throws TreinoNaoCadastradoException {
        Treino treino = repo.buscarTreino(nome);
        repo.apagarTreino(treino);
    }

    public void editarTreino(String nome, String novoNome, List<EnumObjetivoDeExercicio> novoFoco, List<ExercicioPratico> novoExercicioList) throws TreinoNaoCadastradoException{
        Treino treino = repo.buscarTreino(novoNome);
        
        if (novoNome != null && !novoNome.trim().isEmpty()){
            treino.setNome(novoNome);
        }
        if (novoFoco != null && novoFoco.isEmpty()){
            treino.setFoco(novoFoco);
        }
        if (novoExercicioList != null && !novoExercicioList.isEmpty()){
            treino.setExercicioList(novoExercicioList);
        }

    }

    public Treino lerTreino(String nome){
        try{
            Treino treino = repo.buscarTreino(nome);
            System.out.println("Nome:" + nome);
            System.out.println("Foco:" + treino.getFoco());
            System.out.println("Exercicios:" + treino.retornarFicha());
            return treino;
        } catch (TreinoNaoCadastradoException e){
            System.out.println("Erro:" + e.getMessage());
            return null;
        }

    }

    public void adicionarExercicio(String nomeTreino, ExercicioPratico exercicio) throws TreinoNaoCadastradoException{
        Treino treino = repo.buscarTreino(nomeTreino);
        treino.adicionarExercicio(exercicio);
    }


    public void removerExercicio(String nomeTreino, ExercicioPratico exercicio) throws TreinoNaoCadastradoException{
        Treino treino = repo.buscarTreino(nomeTreino);
        if(!treino.removerExercicio(exercicio)){
            System.out.println("Exercicio não encontrado no treino.");
        }
    }

    public void listarExercicios(String nomeTreino) throws TreinoNaoCadastradoException{
        Treino treino = repo.buscarTreino(nomeTreino);
        System.out.println(treino.retornarFicha());
    }

}
