package br.ufrpe.treinos_dietas.negocio.beans.treinos;


import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDoPlano;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class PlanoDeTreinoPorData extends PlanoDeTreino implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate dataFinal;

    public PlanoDeTreinoPorData(String nome, EnumObjetivoDoPlano objetivo, LocalDate dataInicial, LocalDate dataFinal) {
        super(nome, objetivo, dataInicial);
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public int diasFaltando() {
        Period p = Period.between(dataInicial, dataFinal);
        return p.getDays();
    }
}
