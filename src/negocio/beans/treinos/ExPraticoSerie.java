package negocio.beans.treinos;

public abstract class ExPraticoSerie extends ExercicioPratico{
    protected int numDeSeries;
    protected int peso;
    protected int tempoDescanso;

    public ExPraticoSerie(Exercicio exercicio, int numDeSeries, int peso, int tempoDescanso) {
        super(exercicio);
        this.numDeSeries = numDeSeries;
        this.peso = peso;
        this.tempoDescanso = tempoDescanso;
    }

    public int getNumDeSeries() {
        return numDeSeries;
    }

    public void setNumDeSeries(int numDeSeries) {
        this.numDeSeries = numDeSeries;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
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