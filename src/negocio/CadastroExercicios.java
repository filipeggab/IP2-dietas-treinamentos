package negocio;

import Exceptions.ExercicioJaExisteException;
import Exceptions.ExercicioNaoCadastradoException;
import dados.RepositorioExercicios;
import negocio.beans.enums.EnumDificuldade;
import negocio.beans.enums.EnumObjetivoDeExercicio;
import negocio.beans.treinos.Exercicio;

public class CadastroExercicios {
    RepositorioExercicios repo;

    public CadastroExercicios() {
        this.repo = new RepositorioExercicios();
    }

    public void cadastrarExercicio(String nome, String descricao, String materiais, String dificuldade, String objetivo, double percaCaloricaMedia) throws ExercicioJaExisteException {
        try{
            EnumDificuldade enumDif = EnumDificuldade.valueOf(dificuldade);
            EnumObjetivoDeExercicio enumOb = EnumObjetivoDeExercicio.valueOf(objetivo);
            if(!repo.exercicioExiste(nome)){
                Exercicio ex = new Exercicio(nome, descricao, materiais, enumDif, enumOb, percaCaloricaMedia);
                repo.criarExercicio(ex);
            }else{
                throw new ExercicioJaExisteException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Dificuldade ou objetivo inválido.");
            throw e;
        }
    }
    public void removerExercicio(String nome) throws ExercicioNaoCadastradoException{
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
