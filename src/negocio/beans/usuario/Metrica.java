package repository.beans.usuario;

import java.time.LocalDate;

public class Metrica {
    private int altura;
    private double peso;
    private LocalDate dataDaMetrica;
    public Metrica (int altura, double peso){
        this.altura = altura;
        this.peso = peso;
    }
    private double calcularIMC(){
        return peso/altura*altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
