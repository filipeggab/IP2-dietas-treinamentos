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

        ExercicioPratico ex = exercicios.stream().filter(x -> x.getExercicio().getNome().equals(nome)).findFirst().orElse(null);
            if(ex == null){
                throw new ExercicioNaoCadastradoException(nome);
            }
            else{
                return ex;
            }
        }
    public void criarExercicio(ExercicioPratico ex){
        exercicios.add(ex);
    }
}
