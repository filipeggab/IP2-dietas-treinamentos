package br.ufrpe.treinos_dietas.negocio;


import br.ufrpe.treinos_dietas.dados.RepositorioDietas;
import br.ufrpe.treinos_dietas.exceptions.DietaNaoCadastradaException;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDaDieta;

import java.time.LocalDate;

public class CadastroDietas {
    RepositorioDietas repo;

    public CadastroDietas(RepositorioDietas repo) {
        this.repo = repo;
    }


    public void cadastrarDieta(String nome, LocalDate dataInicio, LocalDate dataFim, EnumObjetivoDaDieta  objetivoDaDieta){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da dieta não pode estar vazio.");
        }
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("As datas de início e fim devem ser informadas.");
        }
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("A data de início não pode ser depois da data de fim.");
        }

        Dieta novaDieta = new Dieta(nome, dataInicio, dataFim, objetivoDaDieta);
        repo.criarDieta(novaDieta);
    }

    public void removerDieta(String nome) throws DietaNaoCadastradaException {
        Dieta dieta = repo.buscarDieta(nome);
        repo.apagarDieta(dieta);
    }

    public Dieta lerDieta(String nome) {
        try {
            Dieta dieta = repo.buscarDieta(nome);
            System.out.println("Nome: " + dieta.getNome());
            System.out.println("Início: " + dieta.getDataInicio());
            System.out.println("Fim: " + dieta.getDataFim());
            System.out.println("Total de calorias do dia: " + dieta.caloriasDoDia());
            return dieta;
        } catch (DietaNaoCadastradaException e) {
            System.out.println("Erro:" + e.getMessage());
            return null;
        }
    }

    public void editarDieta(String nome, String novoNome, LocalDate novaDataInicio, LocalDate novaDataFim) throws DietaNaoCadastradaException {
        Dieta dieta = repo.buscarDieta(nome);

        if (novoNome != null && !novoNome.trim().isEmpty()) {
            dieta.setNome(novoNome);
        }
        if (novaDataInicio != null) {
            dieta.setDataInicio(novaDataInicio);
        }
        if (novaDataFim != null) {
            dieta.setDataFim(novaDataFim);
        }
    }

    public void adicionarRefeicao(String nomeDieta, Refeicao refeicao) throws DietaNaoCadastradaException {
        Dieta dieta = repo.buscarDieta(nomeDieta);
        dieta.addRefeicao(refeicao);
    }

    public void removerRefeicao(String nomeDieta, Refeicao refeicao) throws DietaNaoCadastradaException {
        Dieta dieta = repo.buscarDieta(nomeDieta);
        boolean removida = dieta.removerRefeicao(refeicao);

        if (!removida) {
            System.out.println("Refeição não encontrada na dieta.");
        }
    }
}
