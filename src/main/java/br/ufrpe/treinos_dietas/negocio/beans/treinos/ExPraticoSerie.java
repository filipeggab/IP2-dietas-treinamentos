package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.io.Serializable;

public abstract class ExPraticoSerie extends ExercicioPratico implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int numDeSeries;
    protected int tempoDescanso;

    public ExPraticoSerie(Exercicio exercicio, int numDeSeries, int tempoDescanso) {
        super(exercicio);
        this.numDeSeries = numDeSeries;
        this.tempoDescanso = tempoDescanso;
    }

    public int getNumDeSeries() {
        return numDeSeries;
    }

    public void setNumDeSeries(int numDeSeries) {
        this.numDeSeries = numDeSeries;
    }

    public int getTempoDescanso() {
        return tempoDescanso;
    }

    public void setTempoDescanso(int tempoDescanso) {
        this.tempoDescanso = tempoDescanso;
    }

    @Override
    public double percaCalorica() {
        return exercicio.getPercaCaloricaMedia() * numDeSeries;
    }
}