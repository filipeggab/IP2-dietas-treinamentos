package entities.treinos;

public class ExPraticoSerie extends ExercicioPratico{
    private int numDeSeries;
    private int numDeRepeticoes;
    private int peso;
    private int tempoDescanso;

    public ExPraticoSerie(Exercicio exercicio, int numDeSeries, int numDeRepeticoes, int peso, int tempoDescanso) {
        super(exercicio);
        this.numDeSeries = numDeSeries;
        this.numDeRepeticoes = numDeRepeticoes;
        this.peso = peso;
        this.tempoDescanso = tempoDescanso;
    }

    public int getNumDeSeries() {
        return numDeSeries;
    }

    public void setNumDeSeries(int numDeSeries) {
        this.numDeSeries = numDeSeries;
    }

    public int getNumDeRepeticoes() {
        return numDeRepeticoes;
    }

    public void setNumDeRepeticoes(int numDeRepeticoes) {
        this.numDeRepeticoes = numDeRepeticoes;
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
    public int tempoEstimado() {
        return 60*tempoDescanso*numDeSeries; //Levando em conta uma média de 1 minuto por série
    }

    @Override
    public double percaCalorica() {
        return exercicio.getPercaCaloricaMedia() * numDeSeries;
    }
}