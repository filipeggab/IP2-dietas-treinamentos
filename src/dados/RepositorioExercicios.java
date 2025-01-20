package dados;

import Exceptions.ExercicioNaoCadastradoException;
import negocio.beans.treinos.Exercicio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioExercicios {
    List<Exercicio> exercicios;

    public RepositorioExercicios(){
        this.exercicios = new ArrayList<>();
    }
    public Exercicio retornarExercicio(String nome) throws ExercicioNaoCadastradoException{
        Exercicio ex = exercicios.stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
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
    public void apagarExercicio(String nome) throws ExercicioNaoCadastradoException{
        try{
            Exercicio ex = retornarExercicio(nome);
            apagarExercicio(ex);
        } catch (ExercicioNaoCadastradoException e) {
            throw e;
        }
    }

}