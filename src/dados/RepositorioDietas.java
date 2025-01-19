package dados;

import Exceptions.DietaNaoCadastradaException;
import negocio.beans.dietas.Dieta;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDietas {
    List<Dieta> dietas;

    public RepositorioDietas(){
        this.dietas = new ArrayList<>();
    }
    public Dieta buscarDieta(String nome) throws DietaNaoCadastradaException {
        Dieta dieta = dietas.stream().filter(x -> x.getNome().equals(nome)).findFirst().orElse(null);
        if(dieta == null){
            throw new DietaNaoCadastradaException(nome);
        }else{
            return dieta;
        }
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
}
