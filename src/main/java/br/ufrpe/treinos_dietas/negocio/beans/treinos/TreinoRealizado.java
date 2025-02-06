package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.time.LocalDate;

public class TreinoRealizado {
    private Treino treinoRealizado;
    private LocalDate dataRealizada;

    public TreinoRealizado(Treino treinoRealizado, LocalDate dataRealizada) {
        this.treinoRealizado = treinoRealizado;
        this.dataRealizada = dataRealizada;
    }

    public Treino getTreinoRealizado() {
        return treinoRealizado;
    }

    public LocalDate getDataRealizada() {
        return dataRealizada;
    }
}
