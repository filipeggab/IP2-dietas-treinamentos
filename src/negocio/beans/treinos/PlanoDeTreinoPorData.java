package negocio.beans.treinos;

import negocio.beans.enums.EnumObjetivoDoPlano;

import java.time.LocalDate;
import java.time.Period;

public class PlanoDeTreinoPorData extends PlanoDeTreino{
    private LocalDate dataFinal;

    public PlanoDeTreinoPorData(String nome, String nivel, EnumObjetivoDoPlano objetivo, LocalDate dataInicial, LocalDate dataFinal) {
        super(nome, nivel, objetivo, dataInicial);
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
