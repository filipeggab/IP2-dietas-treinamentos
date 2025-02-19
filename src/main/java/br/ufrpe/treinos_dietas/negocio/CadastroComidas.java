package br.ufrpe.treinos_dietas.negocio;

import br.ufrpe.treinos_dietas.integracao.USDAFoodDataAPI;
import br.ufrpe.treinos_dietas.dados.RepositorioComidas;
import br.ufrpe.treinos_dietas.exceptions.ComidaNaoCadastradaException;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;

public class CadastroComidas {
    private RepositorioComidas repo;

    public CadastroComidas() {
        this.repo = new RepositorioComidas();
    }

    public void cadastrarComida(String nome, String unDeMedida) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da comida não pode estar vazio.");
        }
        if (unDeMedida == null || unDeMedida.trim().isEmpty()) {
            throw new IllegalArgumentException("A unidade de medida deve ser informada.");
        }

        USDAFoodDataAPI usdaAPI = new USDAFoodDataAPI();
        try {
            Comida comida = usdaAPI.buscarInformacoesNutricionais(nome);

            Comida novaComida = new Comida(
                    nome,
                    unDeMedida,
                    comida.getProteinas(),
                    comida.getCarboidratos(),
                    comida.getGorduras(),
                    comida.getCalorias()
            );

            repo.criarComida(novaComida);
            System.out.println("Comida cadastrada com sucesso!");
            //System.out.println("Nome:" + nome + "nome dado pela API" + novaComida.getNome() );

        } catch (IOException e) {
            System.out.println("Erro ao buscar informações nutricionais: " + e.getMessage());
        }
    }

    public void removerComida(String nome) throws ComidaNaoCadastradaException {
        repo.apagarComida(nome);
    }


    public Comida lerComida(String nome) {
        try {
            Comida comida = repo.buscarComida(nome);
            System.out.println("Nome: " + comida.getNome());
            System.out.println("Unidade: " + comida.getUnDeMedida());
            System.out.println("Proteínas: " + comida.getProteinas() + "g");
            System.out.println("Carboidratos: " + comida.getCarboidratos() + "g");
            System.out.println("Gorduras: " + comida.getGorduras() + "g");
            System.out.println("Calorias: " + comida.getCalorias() + " kcal");
            return comida;
        } catch (ComidaNaoCadastradaException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public void listarComidas() {
        List<Comida> lista = repo.listarComidas();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma comida cadastrada.");
            return;
        }

        System.out.println("Lista de Comidas:");
        for (Comida comida : lista) {
            System.out.println("- " + comida.getNome() + " (" + comida.getUnDeMedida() + ")");
        }
    }
}