package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.io.Serializable;

public class ExPraticoSerieTempo extends ExPraticoSerie implements Serializable {
    private static final long serialVersionUID = 1L;
    private int tempoDeExec;

    public ExPraticoSerieTempo(Exercicio exercicio, int numDeSeries, int tempoDescanso, int tempoEmSec) {
        super(exercicio, numDeSeries, tempoDescanso);
        this.tempoDeExec = tempoEmSec;
    }

    public int getTempoDeExec() {
        return tempoDeExec;
    }

    public void setTempoDeExec(int tempoDeExec) {
        this.tempoDeExec = tempoDeExec;
    }

    @Override
    public int tempoEstimado() {
        return (numDeSeries * tempoDescanso) + (numDeSeries * tempoDeExec);
    }

    @Override
    public String toString() {
        int minTotal = 0;
        int secTotal = tempoDescanso;
        int minSerie = 0;
        int secSerie = tempoDeExec;
        if(tempoDescanso >= 60) {
            minTotal = tempoDescanso / 60;
            secTotal = tempoDescanso % 60;
        }
        if(tempoDeExec >= 60) {
            minSerie = tempoDescanso / 60;
            secSerie = tempoDescanso % 60;
        }
        return exercicio.getNome() + " | " + numDeSeries + " séries | " + minSerie + "'" + secSerie + "'' reps | " + minTotal + "'" + secTotal + "''";
    }
}
