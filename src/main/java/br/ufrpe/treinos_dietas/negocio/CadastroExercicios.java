package br.ufrpe.treinos_dietas.negocio;


import br.ufrpe.treinos_dietas.dados.RepositorioExercicios;
import br.ufrpe.treinos_dietas.exceptions.ExercicioJaExisteException;
import br.ufrpe.treinos_dietas.exceptions.ExercicioNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Exercicio;

public class CadastroExercicios {
    RepositorioExercicios repo;

    public CadastroExercicios() {
        this.repo = new RepositorioExercicios();
    }

    public void cadastrarExercicio(String nome, String descricao, double percaCaloricaMedia) throws ExercicioJaExisteException {
        try{
            if(!repo.exercicioExiste(nome)){
                Exercicio ex = new Exercicio(nome, descricao, percaCaloricaMedia);
                repo.criarExercicio(ex);
            }else{
                throw new ExercicioJaExisteException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Dificuldade ou objetivo inválido.");
            throw e;
        }
    }
    public void removerExercicio(String nome) throws ExercicioNaoCadastradoException {
        try{
            Exercicio ex = repo.retornarExercicio(nome);
        } catch (ExercicioNaoCadastradoException e) {
            throw e;
        }
    }
    public Exercicio lerExercicio(String nome) throws ExercicioNaoCadastradoException{
        try{
            Exercicio ex = repo.retornarExercicio(nome);
            System.out.println(ex.toString());
            return ex;
        } catch (ExercicioNaoCadastradoException e) {
            throw e;
        }
    }
}
