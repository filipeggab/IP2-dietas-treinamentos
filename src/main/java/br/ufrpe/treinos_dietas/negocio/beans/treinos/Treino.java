package br.ufrpe.treinos_dietas.negocio.beans.treinos;


import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDeExercicio;

import java.util.List;

public class Treino {
    private String nome;
    private List<EnumObjetivoDeExercicio> foco;
    private List<ExercicioPratico> exercicioList;

    public Treino(String nome, List<EnumObjetivoDeExercicio> foco, List<ExercicioPratico> exercicioList) {
        this.nome = nome;
        this.foco = foco;
        this.exercicioList = exercicioList;
    }

    public Treino(String nome, List<EnumObjetivoDeExercicio> foco) {
        this.nome = nome;
        this.foco = foco;
    }

    public boolean adicionarExercicio(ExercicioPratico exercicio){
        if(exercicio instanceof ExPraticoSerieReps || exercicio instanceof ExPraticoSerieTempo || exercicio instanceof ExPraticoCardio){
            exercicioList.add(exercicio);
            return true;
        }else{
            return false;
        }
    }

    public boolean removerExercicio(ExercicioPratico exercicio){
        if(exercicio instanceof ExPraticoSerieReps || exercicio instanceof ExPraticoSerieTempo || exercicio instanceof ExPraticoCardio){
            boolean res = false;
            res = exercicioList.remove(exercicio);
            return res;
        }else{
            return false;
        }
    }

    public int tempoTotal(){
        int res = 0;
        for(ExercicioPratico ex : exercicioList){
            res += ex.tempoEstimado();
        }
        return res;
    }

    public double percaCaloricaTotal(){
        double res = 0;
        for(ExercicioPratico ex : exercicioList){
            res += ex.percaCalorica();
        }
        return res;
    }

    public String retornarFicha(){
        StringBuilder res = new StringBuilder("Ficha\n");
        for(ExercicioPratico ex : exercicioList){
            res.append(ex.toString()).append("\n");
        }
        return res.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<EnumObjetivoDeExercicio> getFoco() {
        return foco;
    }

    public void setFoco(List<EnumObjetivoDeExercicio> foco) {
        this.foco = foco;
    }

    public List<ExercicioPratico> getExercicioList() {
        return exercicioList;
    }

    public void setExercicioList(List<ExercicioPratico> exercicioList) {
        this.exercicioList = exercicioList;
    }
}
