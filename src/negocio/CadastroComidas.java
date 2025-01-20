package negocio;

import dados.RepositorioComidas;
import negocio.beans.dietas.Comida;
import Exceptions.ComidaNaoCadastradaException;

import java.util.List;

public class CadastroComidas {
    private RepositorioComidas repo;

    public CadastroComidas() {
        this.repo = new RepositorioComidas();
    }


    public void cadastrarComida(String nome, String unDeMedida, double proteinas, double carboidratos, double gorduras, double calorias) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da comida não pode estar vazio.");
        }
        if (unDeMedida == null || unDeMedida.trim().isEmpty()) {
            throw new IllegalArgumentException("A unidade de medida deve ser informada.");
        }
        if (proteinas < 0 || carboidratos < 0 || gorduras < 0 || calorias < 0) {
            throw new IllegalArgumentException("Os valores nutricionais não podem ser negativos.");
        }

        Comida novaComida = new Comida(nome, unDeMedida, proteinas, carboidratos, gorduras, calorias);
        repo.criarComida(novaComida);
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
