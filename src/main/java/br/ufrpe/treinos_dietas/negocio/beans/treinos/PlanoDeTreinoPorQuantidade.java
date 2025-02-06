package br.ufrpe.treinos_dietas.negocio.beans.treinos;


import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumDificuldade;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDoPlano;

import java.time.LocalDate;

public class PlanoDeTreinoPorQuantidade extends PlanoDeTreino{
    private int contadorDeDias;
    private int diasTotais;

    public PlanoDeTreinoPorQuantidade(String nome, EnumDificuldade nivel, EnumObjetivoDoPlano objetivo, LocalDate dataInicial, int contadorDeDias, int diasTotais) {
        super(nome, nivel, objetivo, dataInicial);
        this.contadorDeDias = contadorDeDias;
        this.diasTotais = diasTotais;
    }

    public int getContadorDeDias() {
        return contadorDeDias;
    }

    public void setContadorDeDias(int contadorDeDias) {
        this.contadorDeDias = contadorDeDias;
    }

    public int getDiasTotais() {
        return diasTotais;
    }

    public void setDiasTotais(int diasTotais) {
        this.diasTotais = diasTotais;
    }

    @Override
    public int diasFaltando() {
        return diasTotais-contadorDeDias;
    }
}
