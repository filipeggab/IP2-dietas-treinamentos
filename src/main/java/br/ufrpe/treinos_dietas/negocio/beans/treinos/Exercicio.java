package br.ufrpe.treinos_dietas.negocio.beans.treinos;


import java.io.Serializable;

public class Exercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String descricao;
    private double percaCaloricaMedia;

    public Exercicio(String nome, String descricao, double percaCaloricaMedia) {
        this.nome = nome;
        this.descricao = descricao;
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

    public double getPercaCaloricaMedia() {
        return percaCaloricaMedia;
    }

    public void setPercaCaloricaMedia(double percaCaloricaMedia) {
        this.percaCaloricaMedia = percaCaloricaMedia;
    }

    @Override
    public String toString() {
        return "Exercicio{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", percaCaloricaMedia=" + percaCaloricaMedia +
                '}';
    }
}
