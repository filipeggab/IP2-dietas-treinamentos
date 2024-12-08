package entities.treinos;

import java.util.ArrayList;
import java.util.List;

public class Treino {
    private String nome;
    private String foco;
    List<ExercicioPratico> exercicioList = new ArrayList<>();

    public boolean adicionarExercicio(ExercicioPratico exercicio){
        if(exercicio instanceof ExPraticoSerie || exercicio instanceof ExPraticoCardio){
            exercicioList.add(exercicio);
            return true;
        }else{
            return false;
        }
    }

    public boolean removerExercicio(ExercicioPratico exercicio){
        if(exercicio instanceof ExPraticoSerie || exercicio instanceof ExPraticoCardio){
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
}