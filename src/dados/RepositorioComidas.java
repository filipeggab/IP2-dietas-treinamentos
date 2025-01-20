package dados;

import Exceptions.ComidaNaoCadastradaException;
import negocio.beans.dietas.Comida;

import java.util.ArrayList;
import java.util.List;

public class RepositorioComidas {
    List<Comida> comidas;

    public RepositorioComidas(){
        this.comidas = new ArrayList<>();
    }

    public Comida buscarComida(String nome) throws ComidaNaoCadastradaException {
        Comida comida = comidas.stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
        if(comida == null){
            throw new ComidaNaoCadastradaException(nome);
        } else{
            return comida;
        }
    }
    public void criarComida(Comida comida){
        comidas.add(comida);
    }
    public void apagarComida(Comida comida) throws ComidaNaoCadastradaException{
        if(comidas.contains(comida)){
            comidas.remove(comida);
        } else{
            throw new ComidaNaoCadastradaException(comida.getNome());
        }
    }
    public void apagarComida(String nome) throws ComidaNaoCadastradaException {
        try{
            Comida comida = buscarComida(nome);
            comidas.remove(comida);
        } catch (ComidaNaoCadastradaException e) {
            throw e;
        }
    }

    public void atualizarComida(String nome, Comida novaComida) throws ComidaNaoCadastradaException {
        for (int i = 0; i < comidas.size(); i++) {
            if (comidas.get(i).getNome().equalsIgnoreCase(nome)) {
                comidas.set(i, novaComida);
                return; // Sai do loop assim que encontrar
            }
        }
        throw new ComidaNaoCadastradaException(nome);
    }

}
