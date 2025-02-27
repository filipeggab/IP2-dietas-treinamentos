package br.ufrpe.treinos_dietas.negocio.beans.dietas;

import java.io.Serializable;

public class Comida implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private final String qtdEmGramas;
    private final double proteinas;
    private final double carboidratos;
    private final double gorduras;
    private final double calorias;

    public Comida(String nome, String qtdEmGramas, double proteinas, double carboidratos, double gorduras, double calorias) {
        this.nome = nome;
        this.qtdEmGramas = qtdEmGramas;
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.gorduras = gorduras;
        this.calorias = calorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQtdEmGramas() {
        return qtdEmGramas;
    }

    public double getProteinas() {
        return proteinas;
    }

    public double getCarboidratos() {
        return carboidratos;
    }

    public double getGorduras() {
        return gorduras;
    }

    public double getCalorias() {
        return calorias;
    }

    @Override
    public String toString() {
        return qtdEmGramas + " of " + nome;
    }
}
