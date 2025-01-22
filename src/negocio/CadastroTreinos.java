package negocio;


import dados.RepositorioTreinos;
import negocio.beans.treinos.ExPraticoCardio;
import negocio.beans.treinos.ExPraticoSerieReps;
import negocio.beans.treinos.ExPraticoSerieTempo;
import negocio.beans.treinos.Exercicio;
import negocio.beans.treinos.Treino;

public class CadastroTreinos {

    private RepositorioTreinos repo;

    public void CadastroTreinos(){
        this.repo = new RepositorioTreinos();
    }

    public void cadastrarTreino(String nome, String foco){

    }

    public void RemoverTreino(String nome){

    }

    public void editarTreino(String nome, String novoNome, String novoFoco){

    }

    public Treino lerTreino(String nome){
        Treino treino = new Treino(null, null);

        return treino;
    }

    public void adicionarExercicio(ExPraticoCardio exercicio){

    }

    public void adicionarExercicio(ExPraticoSerieReps exercicio){

    }

    public void adicionarExercicio(ExPraticoSerieTempo exercicio){

    }

    public void removerExercicio(String nomeTreino, Exercicio exercicio){

    }

    public void listarExercicios(){

    }

}
