package entities.dietas;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Refeicao {
    private String nome;
    private LocalDateTime horario;
    private ArrayList<Comida> comidas = new ArrayList();

    public Refeicao(String nome, LocalDateTime horario) {
        this.nome = nome;
        this.horario = horario;
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
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
git p
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
