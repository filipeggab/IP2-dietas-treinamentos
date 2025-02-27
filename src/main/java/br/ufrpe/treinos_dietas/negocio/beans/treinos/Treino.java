package br.ufrpe.treinos_dietas.negocio.beans.treinos;


import java.io.Serializable;
import java.util.List;

public class Treino implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private List<ExercicioPratico> exercicioList;

    public Treino(String nome, List<ExercicioPratico> exercicioList) {
        this.nome = nome;
        this.exercicioList = exercicioList;
    }
    public Treino(String nome){
        this.nome = nome;
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

    public List<ExercicioPratico> getExercicioList() {
        return exercicioList;
    }

    public void setExercicioList(List<ExercicioPratico> exercicioList) {
        this.exercicioList = exercicioList;
    }
}
