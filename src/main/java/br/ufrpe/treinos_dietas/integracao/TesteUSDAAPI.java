package br.ufrpe.treinos_dietas.integracao;

import br.ufrpe.treinos_dietas.dados.RepositorioComidas;
import br.ufrpe.treinos_dietas.negocio.CadastroComidas;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;


// pra rodar o teste é só usar: mvn clean compile exec:java "-Dexec.mainClass=br.ufrpe.treinos_dietas.integracao.TesteUSDAAPI"
public class TesteUSDAAPI {
    public static void main(String[] args) {
        USDAFoodDataAPI usdaAPI = new USDAFoodDataAPI();
        RepositorioComidas repositorioComidas = new RepositorioComidas();

        try {
            Comida comida = usdaAPI.buscarInformacoesNutricionais("Chicken Breast", 200); // só trocar essa string aqui pra testar comidas diferentes

            System.out.println("Dados da Comida:");
            System.out.println("Nome: " + comida.getNome());
            System.out.println("Unidade: " + comida.getQtdEmGramas());
            System.out.println("Proteínas: " + comida.getProteinas() + "g");
            System.out.println("Carboidratos: " + comida.getCarboidratos() + "g");
            System.out.println("Gorduras: " + comida.getGorduras() + "g");
            System.out.println("Calorias: " + comida.getCalorias() + " kcal");

            CadastroComidas cadastroComidas = new CadastroComidas(repositorioComidas);
            cadastroComidas.cadastrarComida("Chicken Breast", "g");
            cadastroComidas.lerComida("Chicken Breast");

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}