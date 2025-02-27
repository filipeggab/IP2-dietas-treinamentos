package br.ufrpe.treinos_dietas.negocio.beans.treinos;

import java.io.Serializable;
import java.time.LocalDate;

public class TreinoRealizado implements Serializable {
    private static final long serialVersionUID = 1L;
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
