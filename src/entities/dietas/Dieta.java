package entities.dietas;

import java.util.ArrayList;

public class Dieta {
    private ArrayList<Refeicao> refeicoes = new ArrayList<>();
    public void addRefeicao (Refeicao refeicao){
        this.refeicoes.add(refeicao);
    }
    public double caloriasDoDia() {
        double totalCaloriasDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalCaloriasDoDia += refeicao.caloriasTotais();
        }
        return totalCaloriasDoDia;
    }
    public double proteinasDoDia(){
        double totalProteinasDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalProteinasDoDia += refeicao.proteinasTotais();
        }
        return totalProteinasDoDia;
    }
    public double carboidratosDoDia(){
        double totalCarboidratosDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalCarboidratosDoDia += refeicao.carboidratosTotais();
        }
        return totalCarboidratosDoDia;
    }
    public double gordurasDoDia(){
        double totalGordurasDoDia = 0;
        for (Refeicao refeicao : refeicoes){
            totalGordurasDoDia += refeicao.gordurasTotais();
        }
        return totalGordurasDoDia;
    }

}
