package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.io.Serializable;

public abstract class ExercicioPratico implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Exercicio exercicio;

    public ExercicioPratico(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public abstract int tempoEstimado();
    public abstract double percaCalorica();

    @Override
    public abstract String toString();
}
