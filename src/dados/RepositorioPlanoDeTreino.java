package dados;

import Exceptions.PlanoNaoCadastradoException;
import negocio.beans.treinos.PlanoDeTreino;
import negocio.beans.treinos.PlanoDeTreinoPorData;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPlanoDeTreino {
/*  Tudo comentado pois não sei se vou excluir msm ou nn
    private List<PlanoDeTreino> treinos;

    public RepositorioPlanoDeTreino() {
        this.treinos = new ArrayList<>();
    }

    public void adicionarPlanoDeTreino(PlanoDeTreino planoDeTreino) {
        treinos.add(planoDeTreino);
    }
    public void removerPlanoDeTreino(PlanoDeTreino planoDeTreino) throws PlanoNaoCadastradoException {
        if(treinos.contains(planoDeTreino)){
            treinos.remove(planoDeTreino);
        }
        else{
            throw new PlanoNaoCadastradoException(planoDeTreino.getNome());
        }
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorData() throws PlanoNaoCadastradoException {
        List<PlanoDeTreino> treinosPorData = treinos.stream().filter(x -> x instanceof PlanoDeTreinoPorData).toList();
        return treinosPorData;
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPor */
}
