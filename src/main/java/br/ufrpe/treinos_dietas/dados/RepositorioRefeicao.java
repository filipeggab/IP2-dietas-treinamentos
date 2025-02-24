package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.RefeicaoNaoCadastradaException;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioRefeicao implements Serializable {
    List<Refeicao> refeicoes;

    public RepositorioRefeicao(){
        this.refeicoes = new ArrayList<>();
    }

    public Refeicao buscarRefeicao(String nome) throws RefeicaoNaoCadastradaException {
        Refeicao ref = refeicoes.stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
        if(ref == null){
            throw new RefeicaoNaoCadastradaException(nome);
        }else{
            return ref;
        }
    }

    public List<Refeicao> listarRefeicoes() {
        return new ArrayList<>(refeicoes); // Retorna uma cópia para evitar modificações externas
    }

    public void criarRefeicao(Refeicao refeicao){
        refeicoes.add(refeicao);
    }
    public void apagarRefeicao(Refeicao refeicao) throws RefeicaoNaoCadastradaException{
        if(refeicoes.contains(refeicao)){
            refeicoes.remove(refeicao);
        } else{
            throw new RefeicaoNaoCadastradaException(refeicao.getNome());
        }
    }
    public void apagarRefeicao(String nome) throws RefeicaoNaoCadastradaException{
        try{
            Refeicao ref = buscarRefeicao(nome);
            refeicoes.remove(ref);
        } catch (RefeicaoNaoCadastradaException e) {
            throw e;
        }
    }
}
