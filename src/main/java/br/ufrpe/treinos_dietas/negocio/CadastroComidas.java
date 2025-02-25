package br.ufrpe.treinos_dietas.negocio;

import br.ufrpe.treinos_dietas.dados.RepositorioComidas;
import br.ufrpe.treinos_dietas.exceptions.ComidaNaoCadastradaException;
import br.ufrpe.treinos_dietas.integracao.USDAFoodDataAPI;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;

import java.io.IOException;
import java.util.List;

public class CadastroComidas {
    private RepositorioComidas repo;
    private USDAFoodDataAPI usdaAPI;

    public CadastroComidas(RepositorioComidas repo) {
        this.repo = repo;
        this.usdaAPI = new USDAFoodDataAPI();
    }

    public void cadastrarComida(String nome, String unDeMedida) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da comida não pode estar vazio.");
        }
        if (unDeMedida == null || unDeMedida.trim().isEmpty()) {
            throw new IllegalArgumentException("A unidade de medida deve ser informada.");
        }

        double quantidadeEmGramas;
        try {
            quantidadeEmGramas = Double.parseDouble(unDeMedida.replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A unidade de medida deve conter um número válido em gramas.");
        }

        try {
            Comida comida = usdaAPI.buscarInformacoesNutricionais(nome, quantidadeEmGramas);
            if (comida == null) {
                System.out.println("Alimento não encontrado na API.");
                return;
            }

            Comida novaComida = new Comida(
                    comida.getNome(),
                    quantidadeEmGramas + "g",
                    comida.getProteinas(),
                    comida.getCarboidratos(),
                    comida.getGorduras(),
                    comida.getCalorias()
            );

            repo.criarComida(novaComida);
            System.out.println("Comida cadastrada com sucesso: " + novaComida.getNome());

        } catch (IOException e) {
            System.out.println("Erro ao buscar informações nutricionais: " + e.getMessage());
        }
    }


    public void removerComida(String nome) {
        try {
            repo.apagarComida(nome);
            System.out.println("Comida removida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    public void listarComidas() {
        List<Comida> lista = repo.listarComidas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma comida cadastrada.");
            return;
        }
        System.out.println("Lista de Comidas:");
        lista.forEach(comida -> System.out.println("- " + comida.getNome() + " (" + comida.getQtdEmGramas() + ")"));
    }
    public Comida lerComida(String nome) {
        try {
            Comida comida = repo.buscarComida(nome);
            System.out.println(comida);
            return comida;
        } catch (ComidaNaoCadastradaException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}
