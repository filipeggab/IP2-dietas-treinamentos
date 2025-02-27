package br.ufrpe.treinos_dietas.negocio.beans.dietas;

import java.io.Serializable;
import java.util.ArrayList;

public class Refeicao implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private ArrayList<Comida> comidas = new ArrayList();

    public Refeicao(String nome) {
        this.nome = nome;
    }

    public void addComida (Comida comida){
        this.comidas.add(comida);
    }

    public boolean removerComida (Comida comida){
        boolean res = comidas.remove(comida);
        return res;
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public double carboidratosTotais (){
        double total = 0;
        for (Comida comida : comidas){
            total += comida.getCarboidratos();
        }
        return total;
    }
    public double proteinasTotais(){
        double total = 0;
        for (Comida comida : comidas){
            total += comida.getProteinas();
        }
        return total;
    }
    public double caloriasTotais() {
        double total = 0;
        for (Comida comida : comidas){
            total += comida.getCalorias();
        }
        return total;
    }
    public double gordurasTotais(){
        double total = 0;
        for (Comida comida : comidas){
            total += comida.getGorduras();
        }
        return total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
