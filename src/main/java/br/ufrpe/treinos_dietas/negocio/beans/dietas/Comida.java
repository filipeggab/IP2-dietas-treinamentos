package br.ufrpe.treinos_dietas.negocio.beans.dietas;

public class Comida {
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
