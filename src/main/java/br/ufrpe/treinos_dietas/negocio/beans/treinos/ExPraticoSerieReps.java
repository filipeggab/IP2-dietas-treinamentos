package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.io.Serializable;

public class ExPraticoSerieReps extends ExPraticoSerie implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numDeReps;

    public ExPraticoSerieReps(Exercicio exercicio, int numDeSeries, int tempoDescanso, int numDeReps) {
        super(exercicio, numDeSeries, tempoDescanso);
        this.numDeReps = numDeReps;
    }

    public int getNumDeReps() {
        return numDeReps;
    }

    public void setNumDeReps(int numDeReps) {
        this.numDeReps = numDeReps;
    }

    @Override
    public int tempoEstimado() {
        return (numDeSeries * 60) + (numDeSeries * tempoDescanso); //Levando em conta uma média de 1 minuto por série
    }

    @Override
    public String toString() {
        int min = 0;
        int sec = tempoDescanso;
        if(tempoDescanso >= 60) {
            min = tempoDescanso / 60;
            sec = tempoDescanso % 60;
        }
        return exercicio.getNome() + " | " + numDeSeries + " séries | " + numDeReps + " reps | " + min + "'" + sec + "''";
    }
}
