package br.ufrpe.treinos_dietas.negocio.beans.usuario;

import java.io.Serializable;
import java.time.LocalDate;

public class Metrica implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer altura;
    private Double peso;
    private LocalDate dataDaMetrica;

    public Metrica (int altura, double peso, LocalDate dataDaMetrica){
        this.altura = altura;
        this.peso = peso;
        this.dataDaMetrica = dataDaMetrica;
    }

    public double calcularIMC(){
        double alturaEmMetros = altura / 100.0;
        return peso / (alturaEmMetros * alturaEmMetros);
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

    public LocalDate getDataDaMetrica() {
        return dataDaMetrica;
    }

}