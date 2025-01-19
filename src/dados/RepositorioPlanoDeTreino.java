package dados;

import Exceptions.PlanoNaoCadastradoException;
import negocio.beans.enums.EnumDificuldade;
import negocio.beans.enums.EnumObjetivoDoPlano;
import negocio.beans.treinos.PlanoDeTreino;
import negocio.beans.treinos.PlanoDeTreinoPorData;
import negocio.beans.treinos.PlanoDeTreinoPorQuantidade;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPlanoDeTreino {
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
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorData() {
        List<PlanoDeTreino> treinosPorData = treinos.stream().filter(x -> x instanceof PlanoDeTreinoPorData).toList();
        return treinosPorData;
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorQuantidade() {
        List<PlanoDeTreino> treinosPorQuantidade = treinos.stream().filter(x -> x instanceof PlanoDeTreinoPorQuantidade).toList();
        return treinosPorQuantidade;
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorObjetivo(EnumObjetivoDoPlano obj){
        return treinos.stream().filter(x -> x.getObjetivo() == obj).toList();
    }
    public List<PlanoDeTreino> retornarPlanoDeTreinoPorDificuldade(EnumDificuldade diff){
        return treinos.stream().filter(x -> x.getNivel().equals(diff)).toList();
    }
    public List<PlanoDeTreino> getTreinos(){
        return this.treinos;
    }
}
//teste de commit
