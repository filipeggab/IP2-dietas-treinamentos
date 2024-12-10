package entities.treinos;

import entities.enums.EnumDificuldadeDeTreino;
import entities.enums.EnumObjetivo;

public class Exercicio {
    private String nome;
    private String descricao;
    private String materiais;
    private EnumDificuldadeDeTreino dificuldade;
    private EnumObjetivo objetivo;
    private double percaCaloricaMedia;

    public Exercicio(String nome, String descricao, String materiais, EnumDificuldadeDeTreino dificuldade, EnumObjetivo objetivo, double percaCaloricaMedia) {
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

    public EnumDificuldadeDeTreino getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(EnumDificuldadeDeTreino dificuldade) {
        this.dificuldade = dificuldade;
    }

    public EnumObjetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(EnumObjetivo objetivo) {
        this.objetivo = objetivo;
    }

    public double getPercaCaloricaMedia() {
        return percaCaloricaMedia;
    }

    public void setPercaCaloricaMedia(double percaCaloricaMedia) {
        this.percaCaloricaMedia = percaCaloricaMedia;
    }
}
