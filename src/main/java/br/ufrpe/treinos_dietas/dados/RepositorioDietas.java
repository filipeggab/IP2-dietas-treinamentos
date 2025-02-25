package br.ufrpe.treinos_dietas.dados;


import br.ufrpe.treinos_dietas.exceptions.DietaNaoCadastradaException;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDietas {
    List<Dieta> dietas;
    private static RepositorioDietas instancia;

    public RepositorioDietas(){
        this.dietas = new ArrayList<>();
    }

    public static RepositorioDietas getInstance() {
        if(instancia == null) {
            instancia = new RepositorioDietas();
        }
        return instancia;
    }

    public Dieta buscarDieta(String nome) throws DietaNaoCadastradaException {
        Dieta dieta = dietas.stream().filter(x -> nome.trim().equalsIgnoreCase(x.getNome().trim())).findFirst().orElse(null);
        if(dieta == null){
            throw new DietaNaoCadastradaException(nome);
        }else{
            return dieta;
        }
    }
    public Dieta retornarDieta(String nome) throws DietaNaoCadastradaException {
        if (nome == null) {
            throw new IllegalArgumentException("O nome do plano não pode ser nulo.");
        }
        return dietas.stream()
                .filter(x -> nome.trim().equalsIgnoreCase(x.getNome().trim()))
                .findFirst()
                .orElseThrow(() -> new DietaNaoCadastradaException(nome));
    }
    public String RetornarNomeDaDieta(){
        return dietas.getLast().getNome();
    }
    public void criarDieta(Dieta dieta){
        dietas.add(dieta);
    }
    public void apagarDieta(Dieta dieta) throws DietaNaoCadastradaException{
        if(dietas.contains(dieta)) {
            dietas.remove(dieta);
        }else{
            throw new DietaNaoCadastradaException(dieta.getNome());
        }
    }
    public void apagarConta(String nome) throws DietaNaoCadastradaException{
        try{
            Dieta dieta = buscarDieta(nome);
            dietas.remove(dieta);
        } catch (DietaNaoCadastradaException e) {
            throw e;
        }

    }
}
