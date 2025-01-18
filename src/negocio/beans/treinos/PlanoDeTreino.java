package negocio.beans.treinos;

import negocio.beans.enums.EnumObjetivoDoPlano;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class PlanoDeTreino {
    protected String nome;
    protected String nivel;
    protected EnumObjetivoDoPlano objetivo;
    protected LocalDate dataInicial;
    protected List<Treino> treinoList;
    protected List<TreinoRealizado> treinoRealizadoList;

    public PlanoDeTreino(String nome, String nivel, EnumObjetivoDoPlano objetivo, LocalDate dataInicial) {
        this.nome = nome;
        this.nivel = nivel;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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
}
