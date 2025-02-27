package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.dados.RepositorioDietas;
import br.ufrpe.treinos_dietas.exceptions.RefeicaoNaoCadastradaException;
import br.ufrpe.treinos_dietas.negocio.CadastroRefeicao;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;

import java.text.Normalizer;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MetasEstatisticasGUIController {

    @FXML
    private Button btnTelaDieta;

    @FXML private ProgressBar pbCalorias;
    @FXML private ProgressBar pbProteinas;
    @FXML private ProgressBar pbCarboidratos;
    @FXML private ProgressBar pbGorduras;

    @FXML private Label lblCalorias;
    @FXML private Label lblProteinas;
    @FXML private Label lblCarboidratos;
    @FXML private Label lblGorduras;

    @FXML private Label lblCalCafe, lblProtCafe;
    @FXML private Label lblCalAlmoco, lblProtAlmoco;
    @FXML private Label lblCalLanche, lblProtLanche;
    @FXML private Label lblCalJantar, lblProtJantar;

    @FXML private CheckBox chkCafe, chkAlmoco, chkLanche, chkJantar;

    private double progressoCalorias = 0;
    private double progressoProteinas = 0;
    private double progressoCarboidratos = 0;
    private double progressoGorduras = 0;

    private Dieta dietaAtual;
    private CadastroRefeicao cadastroRefeicao;


    @FXML
    public void btnTelaDietaActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDaDieta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnTelaDieta.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initialize() {
        carregarDieta();
        carregarRefeicoes();
    }

    private void carregarDieta() {
        RepositorioDietas repositorioDietas = RepositorioDietas.getInstance();
        dietaAtual = repositorioDietas.retornarDieta();
    }

    private void carregarRefeicoes() {
        List<Refeicao> refeicoes = dietaAtual.getRefeicoes();

        double metaCalorias = dietaAtual.caloriasDoDia();
        double metaProteinas = dietaAtual.proteinasDoDia();
        double metaCarboidratos = dietaAtual.carboidratosDoDia();
        double metaGorduras = dietaAtual.gordurasDoDia();

        lblCalorias.setText("0/" + formatarNumero(metaCalorias) + " kcal");
        lblProteinas.setText("0/" + formatarNumero(metaProteinas) + "g");
        lblCarboidratos.setText("0/" + formatarNumero(metaCarboidratos) + "g");
        lblGorduras.setText("0/" + formatarNumero(metaGorduras) + "g");

        if (refeicoes.size() >= 4) {
            configurarRefeicao(refeicoes.get(0), lblCalCafe, lblProtCafe, chkCafe);
            configurarRefeicao(refeicoes.get(1), lblCalAlmoco, lblProtAlmoco, chkAlmoco);
            configurarRefeicao(refeicoes.get(2), lblCalLanche, lblProtLanche, chkLanche);
            configurarRefeicao(refeicoes.get(3), lblCalJantar, lblProtJantar, chkJantar);
        }
    }

    private void configurarRefeicao(Refeicao refeicao, Label lblCal, Label lblProt, CheckBox chk) {
        double calorias = refeicao.caloriasTotais();
        double proteinas = refeicao.proteinasTotais();

        lblCal.setText(formatarNumero(calorias) + " kcal");
        lblProt.setText(formatarNumero(proteinas) + "g");
        chk.setOnAction(event -> atualizarProgresso(refeicao, chk.isSelected()));
    }

    private void atualizarProgresso(Refeicao refeicao, boolean adicionando) {
        double fator = adicionando ? 1 : -1;

        progressoCalorias += fator * refeicao.caloriasTotais();
        progressoProteinas += fator * refeicao.proteinasTotais();
        progressoCarboidratos += fator * refeicao.carboidratosTotais();
        progressoGorduras += fator * refeicao.gordurasTotais();

        double metaCalorias = dietaAtual.caloriasDoDia();
        double metaProteinas = dietaAtual.proteinasDoDia();
        double metaCarboidratos = dietaAtual.carboidratosDoDia();
        double metaGorduras = dietaAtual.gordurasDoDia();

        pbCalorias.setProgress(progressoCalorias / metaCalorias);
        pbProteinas.setProgress(progressoProteinas / metaProteinas);
        pbCarboidratos.setProgress(progressoCarboidratos / metaCarboidratos);
        pbGorduras.setProgress(progressoGorduras / metaGorduras);

        lblCalorias.setText(formatarNumero(progressoCalorias) + "/" + formatarNumero(metaCalorias) + " kcal");
        lblProteinas.setText(formatarNumero(progressoProteinas) + "/" + formatarNumero(metaProteinas) + "g");
        lblCarboidratos.setText(formatarNumero(progressoCarboidratos) + "/" + formatarNumero(metaCarboidratos) + "g");
        lblGorduras.setText(formatarNumero(progressoGorduras) + "/" + formatarNumero(metaGorduras) + "g");
    }

    private String formatarNumero(double numero) {
        return String.format("%.2f", numero).replace(".", ",");
    }
}
