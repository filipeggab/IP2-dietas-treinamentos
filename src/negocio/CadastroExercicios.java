package negocio;

import dados.RepositorioExercicios;
import negocio.beans.enums.EnumDificuldade;
import negocio.beans.enums.EnumObjetivoDeExercicio;

public class CadastroExercicios {
    RepositorioExercicios repo;

    public CadastroExercicios() {
        this.repo = new RepositorioExercicios();
    }

    public void cadastrarExercicio(String nome, String descricao, String materiais, String dificuldade, String objetivo, double percaCaloricaMedia){
        try{
            EnumDificuldade enumDif = EnumDificuldade.valueOf(dificuldade);
            EnumObjetivoDeExercicio enumOb = EnumObjetivoDeExercicio.valueOf(objetivo);
        } catch (IllegalArgumentException e) {
            System.out.println("Dificuldade ou objetivo inválido.");
            throw e;
        }
    }
}
