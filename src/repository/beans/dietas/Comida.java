package repository.entities.dietas;

public class Comida {
    private String nome;
    private String unDeMedida;
    private double proteinas;
    private double carboidratos;
    private double gorduras;
    private double calorias;

    public Comida(String nome, String unDeMedida, double proteinas, double carboidratos, double gorduras, double calorias) {
        this.nome = nome;
        this.unDeMedida = unDeMedida;
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

    public String getUnDeMedida() {
        return unDeMedida;
    }

    public void setUnDeMedida(String unDeMedida) {
        this.unDeMedida = unDeMedida;
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
