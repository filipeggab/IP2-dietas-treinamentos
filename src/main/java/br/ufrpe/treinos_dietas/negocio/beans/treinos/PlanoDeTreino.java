package br.ufrpe.treinos_dietas.negocio.beans.treinos;


import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDoPlano;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class PlanoDeTreino {
    protected String nome;
    protected EnumObjetivoDoPlano objetivo;
    protected LocalDate dataInicial;
    protected List<Treino> treinoList;
    protected List<TreinoRealizado> treinoRealizadoList;
    protected int contagemDeDias = 0;

    public PlanoDeTreino(String nome, EnumObjetivoDoPlano objetivo, LocalDate dataInicial) {
        this.nome = nome;
        this.objetivo = objetivo;
        this.dataInicial = dataInicial;
        this.treinoList = new ArrayList<>();
        this.treinoRealizadoList = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumObjetivoDoPlano getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(EnumObjetivoDoPlano objetivo) {
        this.objetivo = objetivo;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setTreinoList(List<Treino> treinoList) {
        this.treinoList = treinoList;
    }

    public List<Treino> getTreinoList(){
        return this.treinoList;
    }

    public abstract int diasFaltando();

    public void adicionarTreino(Treino treino){
        treinoList.add(treino);
    }

    public void removerTreino(Treino treino){
        treinoList.remove(treino);
    }

    public void adicionarTreinoRealizado(Treino treino){
        if(treinoList.contains(treino)){
            TreinoRealizado treinoRealizado = new TreinoRealizado(treino, LocalDate.now());
            treinoRealizadoList.add(treinoRealizado);
        }
    }
    public List<TreinoRealizado> getTreinoRealizadoList() {
        return treinoRealizadoList;
    }
}
