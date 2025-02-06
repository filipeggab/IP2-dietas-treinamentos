package br.ufrpe.treinos_dietas.negocio;


import br.ufrpe.treinos_dietas.dados.RepositorioRefeicao;
import br.ufrpe.treinos_dietas.exceptions.RefeicaoNaoCadastradaException;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;

import java.time.LocalDateTime;
import java.util.List;

public class CadastroRefeicao {
    private RepositorioRefeicao repo;

    public CadastroRefeicao() {
        this.repo = new RepositorioRefeicao();
    }

    public void cadastrarRefeicao(String nome, LocalDateTime horario) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da refeição não pode estar vazio.");
        }
        if (horario == null) {
            throw new IllegalArgumentException("O horário da refeição deve ser informado.");
        }

        Refeicao novaRefeicao = new Refeicao(nome, horario);
        repo.criarRefeicao(novaRefeicao);
    }

    public void removerRefeicao(String nome) throws RefeicaoNaoCadastradaException {
        repo.apagarRefeicao(nome);
    }

    public Refeicao lerRefeicao(String nome) {
        try {
            Refeicao refeicao = repo.buscarRefeicao(nome);
            System.out.println("Nome: " + refeicao.getNome());
            System.out.println("Horário: " + refeicao.getHorario());
            System.out.println("Total de calorias: " + refeicao.caloriasTotais());
            return refeicao;
        } catch (RefeicaoNaoCadastradaException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public void editarRefeicao(String nome, String novoNome, LocalDateTime novoHorario) throws RefeicaoNaoCadastradaException {
        Refeicao refeicao = repo.buscarRefeicao(nome);

        if (novoNome != null && !novoNome.trim().isEmpty()) {
            refeicao.setNome(novoNome);
        }
        if (novoHorario != null) {
            refeicao.setHorario(novoHorario);
        }
    }

    public void adicionarComida(String nomeRefeicao, Comida comida) throws RefeicaoNaoCadastradaException {
        Refeicao refeicao = repo.buscarRefeicao(nomeRefeicao);
        refeicao.addComida(comida);
    }

    public void removerComida(String nomeRefeicao, Comida comida) throws RefeicaoNaoCadastradaException {
        Refeicao refeicao = repo.buscarRefeicao(nomeRefeicao);
        boolean removida = refeicao.removerComida(comida);

        if (!removida) {
            System.out.println("Comida não encontrada na refeição.");
        }
    }

    public void listarRefeicoes() {
        List<Refeicao> lista = repo.listarRefeicoes();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma refeição cadastrada.");
            return;
        }

        System.out.println("Lista de Refeições:");
        for (Refeicao refeicao : lista) {
            System.out.println("- " + refeicao.getNome() + " às " + refeicao.getHorario());
        }
    }

}
