package repository.beans.treinos;

public abstract class ExercicioPratico {

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
