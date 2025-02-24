package br.ufrpe.treinos_dietas.negocio.beans.usuario;

import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumSexo;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private String senha;
    private EnumSexo sexo;
    private LocalDate dataNasc;
    private List<Metrica> metricas;
    private List<PlanoDeTreino> planoDeTreinoList;
    private List<Dieta> dietaList;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha, EnumSexo sexo, LocalDate dataNasc) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.metricas = new ArrayList<>();
    }

    public Usuario() {
        this.metricas = new ArrayList<>();
        this.planoDeTreinoList = new ArrayList<>();
        this.dietaList = new ArrayList<>();
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

    public void adicionarMetrica(Metrica metrica){
        this.metricas.add(metrica);
    }
    public void adicionarDieta(Dieta dieta){
        this.dietaList.add(dieta);
    }
    public void adicionarPlanoDeTreino(PlanoDeTreino planoDeTreino){
        this.planoDeTreinoList.add(planoDeTreino);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", sexo=" + sexo +
                ", dataNasc=" + dataNasc +
                ", metricas=" + metricas +
                ", planoDeTreinoList=" + planoDeTreinoList +
                ", dietaList=" + dietaList +
                '}';
    }
}
