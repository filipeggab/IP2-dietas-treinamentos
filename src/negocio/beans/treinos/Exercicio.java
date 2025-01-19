package negocio.beans.treinos;

import negocio.beans.enums.EnumDificuldade;
import negocio.beans.enums.EnumObjetivoDeExercicio;

public class Exercicio {
    private String nome;
    private String descricao;
    private String materiais;
    private EnumDificuldade dificuldade;
    private EnumObjetivoDeExercicio objetivo;
    private double percaCaloricaMedia;

    public Exercicio(String nome, String descricao, String materiais, EnumDificuldade dificuldade, EnumObjetivoDeExercicio objetivo, double percaCaloricaMedia) {
        this.nome = nome;
        this.descricao = descricao;
        this.materiais = materiais;
        this.dificuldade = dificuldade;
        this.objetivo = objetivo;
        this.percaCaloricaMedia = percaCaloricaMedia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMateriais() {
        return materiais;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }

    public EnumDificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(EnumDificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public EnumObjetivoDeExercicio getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(EnumObjetivoDeExercicio objetivo) {
        this.objetivo = objetivo;
    }

    public double getPercaCaloricaMedia() {
        return percaCaloricaMedia;
    }

    public void setPercaCaloricaMedia(double percaCaloricaMedia) {
        this.percaCaloricaMedia = percaCaloricaMedia;
    }
}
