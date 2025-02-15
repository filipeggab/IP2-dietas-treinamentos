package br.ufrpe.treinos_dietas.negocio.beans.dietas;

import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDaDieta;

import java.time.LocalDate;
import java.util.ArrayList;

public class Dieta {
    private String nome;
    private ArrayList<Refeicao> refeicoes;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private EnumObjetivoDaDieta objetivoDaDieta;

    public Dieta() {
        this.refeicoes = new ArrayList<>();
    }

    public Dieta(String nome, LocalDate dataInicio, LocalDate dataFim, EnumObjetivoDaDieta objetivoDaDieta) {
        this.nome = nome;
        this.refeicoes = new ArrayList<>();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.objetivoDaDieta = objetivoDaDieta;
    }


    public ArrayList<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addRefeicao (Refeicao refeicao){
        this.refeicoes.add(refeicao);
    }

    public boolean removerRefeicao(Refeicao refeicao){
        boolean res = refeicoes.remove(refeicao);
        return res;
    }

    public double caloriasDoDia() {
        double totalCaloriasDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalCaloriasDoDia += refeicao.caloriasTotais();
        }
        return totalCaloriasDoDia;
    }
    public double proteinasDoDia(){
        double totalProteinasDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalProteinasDoDia += refeicao.proteinasTotais();
        }
        return totalProteinasDoDia;
    }
    public double carboidratosDoDia(){
        double totalCarboidratosDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalCarboidratosDoDia += refeicao.carboidratosTotais();
        }
        return totalCarboidratosDoDia;
    }
    public double gordurasDoDia(){
        double totalGordurasDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalGordurasDoDia += refeicao.gordurasTotais();
        }
        return totalGordurasDoDia;
    }

    public void setObjetivoDaDieta(EnumObjetivoDaDieta objetivoDaDieta) {
        this.objetivoDaDieta = objetivoDaDieta;
    }
}
