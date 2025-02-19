package br.ufrpe.treinos_dietas.dados;



import br.ufrpe.treinos_dietas.exceptions.ComidaNaoCadastradaException;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;

import java.util.ArrayList;
import java.util.List;

public class RepositorioComidas {
    List<Comida> comidas;

    public RepositorioComidas() {
        this.comidas = new ArrayList<>();
    }

    public Comida buscarComida(String nome) throws ComidaNaoCadastradaException {
        Comida comida = comidas.stream().filter(x -> nome.trim().equalsIgnoreCase(x.getNome().trim())).findFirst().orElse(null);
        if (comida == null) {
            throw new ComidaNaoCadastradaException(nome);
        } else {
            return comida;
        }
    }

    public List<Comida> listarComidas() {
        return new ArrayList<>(comidas);
    }

    public void criarComida(Comida comida) {
        comidas.add(comida);
    }

    public void apagarComida(Comida comida) throws ComidaNaoCadastradaException {
        if (comidas.contains(comida)) {
            comidas.remove(comida);
        } else {
            throw new ComidaNaoCadastradaException(comida.getNome());
        }
    }

    public void apagarComida(String nome) throws ComidaNaoCadastradaException {
        try {
            Comida comida = buscarComida(nome);
            comidas.remove(comida);
        } catch (ComidaNaoCadastradaException e) {
            throw e;
        }
    }

}
