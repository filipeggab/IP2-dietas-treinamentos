package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.ExercicioNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Exercicio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioExercicios {
    List<Exercicio> exercicios;

    public RepositorioExercicios(){
        this.exercicios = new ArrayList<>();
    }
    public Exercicio retornarExercicio(String nome) throws ExercicioNaoCadastradoException{
        Exercicio ex = exercicios.stream().filter(x -> nome.trim().equalsIgnoreCase(x.getNome().trim())).findFirst().orElse(null);
        if(ex == null){
            throw new ExercicioNaoCadastradoException(nome);
        } else{
            return ex;
        }
    }
    public boolean exercicioExiste(String nome){
        try{
            retornarExercicio(nome);
            return true;
        } catch (ExercicioNaoCadastradoException e) {
            return false;
        }
    }
    public void criarExercicio(Exercicio ex){
        exercicios.add(ex);
    }
    public void apagarExercicio(Exercicio ex) throws ExercicioNaoCadastradoException {
        if(exercicios.contains(ex)){
            exercicios.remove(ex);
        } else{
            throw new ExercicioNaoCadastradoException(ex.getNome());
        }
    }

}