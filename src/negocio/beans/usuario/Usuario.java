package repository.beans.usuario;
import repository.beans.enums.EnumSexo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private EnumSexo sexo;
    private LocalDate dataNasc;
    private ArrayList<Metrica> metricas = new ArrayList<Metrica>();

    public Usuario(String nome, String email, String senha, EnumSexo sexo, LocalDate dataNasc) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
    }

    public Metrica ultimaMetrica (){
        return metricas.getLast();
    }
    
    public void addMetrica (Metrica metrica){
        metricas.add(metrica);
    }

    public int calcularIdade(){
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumSexo getSexo() {
        return sexo;
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

}
