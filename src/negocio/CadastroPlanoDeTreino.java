package negocio;

import java.time.LocalDate;

import dados.RepositorioPlanoDeTreino;
import negocio.beans.treinos.Treino;
import negocio.beans.treinos.PlanoDeTreino;

public class CadastroPlanoDeTreino {
    private RepositorioPlanoDeTreino repo;

    public void CadastroPlanoDeTreino(){
        this.repo = new RepositorioPlanoDeTreino();
    }

    public void cadastrarPlanoDeTreino(String nome, String nivel, String objetivo, LocalDate dataInicial){

    }

    public void removerPlanoDeTreino(String nome){

    }

    public PlanoDeTreino lerPlanoDeTreino(String nome){

        return null;
    }

    public void editarPlanoDeTreino(String nome, String novoNome, String novoNivel, String novoObjetivo, LocalDate novaDataInicial){

    }

    public void adicionarTreino(String nomeTreino, Treino treino){

    }

    public void removerRefeicao(String nomeTreino, Treino treino){
        
    }
}
