package negocio;

import java.time.LocalDate;

import Exceptions.PlanoNaoCadastradoException;
import dados.RepositorioPlanoDeTreino;
import negocio.beans.treinos.Treino;
import negocio.beans.enums.EnumDificuldade;
import negocio.beans.enums.EnumObjetivoDoPlano;
import negocio.beans.treinos.PlanoDeTreino;
import negocio.beans.treinos.PlanoDeTreinoPorData;
import negocio.beans.treinos.PlanoDeTreinoPorQuantidade;

public class CadastroPlanoDeTreino {
    private RepositorioPlanoDeTreino repo;

    public void CadastroPlanoDeTreino(){
        this.repo = new RepositorioPlanoDeTreino();
    }

    //overload para os dois tipos de PLano de Treino
    //por data
    public void cadastrarPlanoDeTreino(String nome, EnumDificuldade nivel, EnumObjetivoDoPlano objetivo, LocalDate dataInicial, LocalDate dataFinal){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do plano não pode estar vazio");
        }
        if (nivel == null){
            throw new IllegalArgumentException("O nível do plano não pode ser nulo");
        }
        if (objetivo == null){
            throw new IllegalArgumentException("O objetivo não pode ser nulo");
        }
        if (dataInicial == null){
            throw new IllegalArgumentException("A data inicial não pode ser nula");
        }
        PlanoDeTreino planoDeTreino = new PlanoDeTreinoPorData(nome, nivel, objetivo, dataInicial, dataFinal);
        repo.criarPlanoDeTreino(planoDeTreino);
    }

    //por quantidade
    public void cadastrarPlanoDeTreino(String nome, EnumDificuldade nivel, EnumObjetivoDoPlano objetivo, LocalDate dataInicial, int contadorDeDias, int diasTotais){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do plano não pode estar vazio");
        }
        if (nivel == null){
            throw new IllegalArgumentException("O nível do plano não pode ser nulo");
        }
        if (objetivo == null){
            throw new IllegalArgumentException("O objetivo não pode ser nulo");
        }
        if (dataInicial == null){
            throw new IllegalArgumentException("A data inicial não pode ser nula");
        }
        PlanoDeTreino planoDeTreino = new PlanoDeTreinoPorQuantidade(nome, nivel, objetivo, dataInicial, contadorDeDias, diasTotais);
        repo.criarPlanoDeTreino(planoDeTreino);
    }

    public void removerPlanoDeTreino(String nome) throws PlanoNaoCadastradoException{
        PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nome);
        repo.apagarPlanoDeTreino(planoDeTreino);
    }

    public PlanoDeTreino lerPlanoDeTreino(String nome){
        try{
            PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nome);
            System.out.println("Nome:" + planoDeTreino.getNome());
            System.out.println("Nivel:" + planoDeTreino.getNivel());
            System.out.println("Objetivo:" + planoDeTreino.getObjetivo());
            System.out.println("Data Inicial:" + planoDeTreino.getDataInicial());
            if(planoDeTreino instanceof PlanoDeTreinoPorData){
                System.out.println("Data Final:" + ((PlanoDeTreinoPorData) planoDeTreino).getDataFinal());
            } else if (planoDeTreino instanceof PlanoDeTreinoPorQuantidade){
                System.out.println("ContadorDeDias:" + ((PlanoDeTreinoPorQuantidade) planoDeTreino).getContadorDeDias());
                System.out.println("Dias Totais:" + ((PlanoDeTreinoPorQuantidade) planoDeTreino).getDiasTotais());
            }
            System.out.println("");
            return planoDeTreino;

        } catch (PlanoNaoCadastradoException e){
            System.out.println("Erro:" + e.getMessage());
            return null;
        }
    }

    //metodos overloaded
    //para PlanoDeTreinoPorQuantidade
    public void editarPlanoDeTreino(String nome, String novoNome, EnumDificuldade novoNivel, EnumObjetivoDoPlano novoObjetivo,
        LocalDate novaDataInicial, int novoContadorDeDias, int novoDiasTotais) throws PlanoNaoCadastradoException
    {
        PlanoDeTreinoPorQuantidade planoDeTreino = (PlanoDeTreinoPorQuantidade) repo.retornarPlanoDeTreino(nome);

        if(novoNome != null && !novoNome.trim().isEmpty()){
            planoDeTreino.setNome(novoNome);
        }
        if(novoNivel != null){
            planoDeTreino.setNivel(novoNivel);
        }
        if(novoObjetivo != null){
            planoDeTreino.setObjetivo(novoObjetivo);
        }
        if(novaDataInicial != null){
            planoDeTreino.setDataInicial(novaDataInicial);
        }
        planoDeTreino.setContadorDeDias(novoContadorDeDias);
        planoDeTreino.setDiasTotais(novoDiasTotais);

    }

    //para PlanoDeTreinoPorData
    public void editarPlanoDeTreino(String nome, String novoNome, EnumDificuldade novoNivel, EnumObjetivoDoPlano novoObjetivo,
        LocalDate novaDataInicial, LocalDate novaDataFinal) throws PlanoNaoCadastradoException
     {
        PlanoDeTreinoPorData planoDeTreino = (PlanoDeTreinoPorData) repo.retornarPlanoDeTreino(nome);

        if(novoNome != null && !novoNome.trim().isEmpty()){
            planoDeTreino.setNome(novoNome);
        }
        if(novoNivel != null){
            planoDeTreino.setNivel(novoNivel);
        }
        if(novoObjetivo != null){
            planoDeTreino.setObjetivo(novoObjetivo);
        }
        if(novaDataInicial != null){
            planoDeTreino.setDataInicial(novaDataInicial);
        }
        if(novaDataFinal != null){
            planoDeTreino.setDataFinal(novaDataFinal);
        }
    }

    public void adicionarTreino(String nomePlanoTreino, Treino treino) throws PlanoNaoCadastradoException{
        PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nomePlanoTreino);
        planoDeTreino.adicionarTreino(treino);
    }

    public void removerTreino(String nomePlanoTreino, Treino treino) throws PlanoNaoCadastradoException{
        PlanoDeTreino planoDeTreino = repo.retornarPlanoDeTreino(nomePlanoTreino);
        planoDeTreino.removerTreino(treino);
    }
}
