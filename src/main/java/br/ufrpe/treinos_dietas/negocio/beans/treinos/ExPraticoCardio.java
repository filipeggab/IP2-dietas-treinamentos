package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.io.Serializable;

public class ExPraticoCardio extends ExercicioPratico implements Serializable {
    private int tempoEmSec;
    private String intensidade;

    public ExPraticoCardio(Exercicio exercicio, int tempoEmSec, String intensidade) {
        super(exercicio);
        this.tempoEmSec = tempoEmSec;
        this.intensidade = intensidade;
    }

    public void setTempoEmSec(int tempoEmSec) {
        this.tempoEmSec = tempoEmSec;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }

    @Override
    public int tempoEstimado() {
        return tempoEmSec;
    }

    @Override
    public double percaCalorica() {
        return exercicio.getPercaCaloricaMedia()*tempoEmSec;
    }

    @Override
    public String toString() {
        int min = 0;
        int sec = tempoEmSec;
        if(tempoEmSec >= 60) {
            min = tempoEmSec / 60;
            sec = tempoEmSec % 60;
        }
        return exercicio.getNome() + " | " + min + "'" + sec + "''";
    }
}