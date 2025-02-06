package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.TreinoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Treino;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTreinos {
    List<Treino> treinos;

    public RepositorioTreinos() {
        this.treinos = new ArrayList<>();
    }
    public void criarTreino(Treino treino) {
        treinos.add(treino);
    }
    public void apagarTreino(Treino treino) throws TreinoNaoCadastradoException {
        if(treinos.contains(treino)){
            treinos.remove(treino);
        }
        else{
            throw new TreinoNaoCadastradoException(treino.getNome());
        }
    }
    public Treino buscarTreino(String nome) throws TreinoNaoCadastradoException{
        Treino treino = treinos.stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
        if(treino == null){
            throw new TreinoNaoCadastradoException(nome);
        } else{
            return treino;
        }
    }
}
