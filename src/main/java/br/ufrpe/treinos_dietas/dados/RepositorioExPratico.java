package br.ufrpe.treinos_dietas.dados;

import br.ufrpe.treinos_dietas.exceptions.ExercicioNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.ExercicioPratico;

import java.util.ArrayList;
import java.util.List;

public class RepositorioExPratico {
    private List<ExercicioPratico> exercicios;

    public RepositorioExPratico(){
        this.exercicios = new ArrayList<ExercicioPratico>();
    }

    public ExercicioPratico retornarExPratico(String nome) throws ExercicioNaoCadastradoException {
        ExercicioPratico exerciciop = null;

        for(ExercicioPratico ex:this.exercicios) {
            if (ex.getExercicio().getNome().equals(nome)) {
                exerciciop = ex;
                break;
            }
        }
            if(exerciciop == null){
                throw new ExercicioNaoCadastradoException(nome);
            }
            else{
                return exerciciop;
            }
        }
    public void criarExercicio(ExercicioPratico ex){
        exercicios.add(ex);
    }
}
