package br.ufrpe.treinos_dietas.negocio.beans.usuario;

import br.ufrpe.treinos_dietas.dados.RepositorioDietas;
import br.ufrpe.treinos_dietas.dados.RepositorioPlanoDeTreino;
import br.ufrpe.treinos_dietas.exceptions.DietaNaoCadastradaException;
import br.ufrpe.treinos_dietas.exceptions.PlanoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumSexo;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Treino;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.TreinoRealizado;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private EnumSexo sexo;
    private LocalDate dataNasc;
    private final List<Metrica> metricas;
    private List<PlanoDeTreino> planoDeTreinoList;
    private List<Dieta> dietaList;
    private Dieta dietaAtual;
    private PlanoDeTreino planoDeTreinoAtual;
    private int contador = 0;
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();
    RepositorioDietas repositorioDietas = RepositorioDietas.getInstance();

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

    public Metrica ultimaMetrica() {
        if (metricas.isEmpty()) {
            return null; // null se não houver métricas
        }
        return metricas.getLast();
    }

    public List<Metrica> getMetricaList(){
        return metricas;
    }

    public List<Dieta> getListDietas(){
        return dietaList;
    }
    public List<PlanoDeTreino> getPlanoDeTreinoList(){
        return planoDeTreinoList;
    }
    public PlanoDeTreino getPlanoDeTreinoAtual(){
        return planoDeTreinoAtual;
    }
    public void setPlanoDeTreinoAtual(PlanoDeTreino planoAtual){
        this.planoDeTreinoAtual = planoAtual;
    }
    public void setDietaAtual(Dieta dietaAtual){
        this.dietaAtual = dietaAtual;
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

    public void setPlanoDeTreinoList(List<PlanoDeTreino> planoDeTreinoList) {
        this.planoDeTreinoList = planoDeTreinoList;
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

    public void AdicionarListaAoPlanoDeTreino(String nome, List<Treino> lista) throws PlanoNaoCadastradoException {
        PlanoDeTreino plano = repositorioPlanoDeTreino.retornarPlanoDeTreino(nome);

        if (plano == null) {
            throw new PlanoNaoCadastradoException("Plano de treino " + nome + " não encontrado.");
        }

        plano.setTreinoList(lista != null ? new ArrayList<>(lista) : new ArrayList<>());
    }

    public void AdicionarListaADieta(String nome, List<Refeicao> lista) throws DietaNaoCadastradaException {
        Dieta dieta = repositorioDietas.retornarDieta(nome);
        if (dieta == null) {
            throw new DietaNaoCadastradaException(nome);
        }
        dieta.setRefeicoesList(lista != null ? new ArrayList<>(lista) : new ArrayList<>());
    }

    public void acrescentarContador(){
        this.contador++;
    }

    public int getContador(){
        return contador;
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


    public Dieta getDietaAtual() {
        return dietaAtual;
    }
}
