package br.ufrpe.treinos_dietas.negocio.beans.dietas;

public class Comida {
    private String nome;
    private String qtdEmGramas;
    private double proteinas;
    private double carboidratos;
    private double gorduras;
    private double calorias;

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

    public void setQtdEmGramas(String qtdEmGramas) {
        this.qtdEmGramas = qtdEmGramas;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(double carboidratos) {
        this.carboidratos = carboidratos;
    }

    public double getGorduras() {
        return gorduras;
    }

    public void setGorduras(double gorduras) {
        this.gorduras = gorduras;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }
}
