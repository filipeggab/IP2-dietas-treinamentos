package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.TreinoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Treino;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTreinos implements Serializable {
    private static final long serialVersionUID = 1L;
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
        Treino treino = treinos.stream().filter(x -> nome.trim().equalsIgnoreCase(x.getNome().trim())).findFirst().orElse(null);
        if(treino == null){
            throw new TreinoNaoCadastradoException(nome);
        } else{
            return treino;
        }
    }
}
