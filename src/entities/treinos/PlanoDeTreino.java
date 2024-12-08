package entities.treinos;

import java.time.LocalDate;

public abstract class PlanoDeTreino {
    protected String nome;
    protected String nivel;
    protected String objetivo;
    protected LocalDate dataInicial;

    public PlanoDeTreino(String nome, String nivel, String objetivo, LocalDate dataInicial) {
        this.nome = nome;
        this.nivel = nivel;
        this.objetivo = objetivo;
        this.dataInicial = dataInicial;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public abstract int diasFaltando();
}
