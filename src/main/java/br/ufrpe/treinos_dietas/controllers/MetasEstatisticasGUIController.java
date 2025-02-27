package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.CadastroRefeicao;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

public class MetasEstatisticasGUIController {

    private Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
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


    private final Preferences prefs = Preferences.userNodeForPackage(MetasEstatisticasGUIController.class);

    private void salvarEstadoCheckbox(String chave, boolean estado) {
        prefs.putBoolean(chave, estado);
    }

    private boolean recuperarEstadoCheckbox(String chave) {
        return prefs.getBoolean(chave, false); // false é o valor padrão caso não exista
    }

    @FXML
    public void btnTelaDietaActionPerformed() throws IOException {
        VoltarParaTelaDaDieta(btnTelaDieta);
    }

    public static void VoltarParaTelaDaDieta(Button btnTelaDieta) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDaDieta.fxml"));
        Parent root = loader.load();
        TelaDaDietaGUIController controller = loader.getController();
        controller.alocadorLabelsDietas();
        Stage stage = (Stage) btnTelaDieta.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initialize() {
        carregarDieta();
        carregarRefeicoes();

        chkCafe.setSelected(recuperarEstadoCheckbox("chkCafe"));
        chkAlmoco.setSelected(recuperarEstadoCheckbox("chkAlmoco"));
        chkLanche.setSelected(recuperarEstadoCheckbox("chkLanche"));
        chkJantar.setSelected(recuperarEstadoCheckbox("chkJantar"));

        atualizarProgressoGeral();

        chkCafe.setOnAction(event -> {
            salvarEstadoCheckbox("chkCafe", chkCafe.isSelected());
            atualizarProgresso(dietaAtual.getRefeicoes().get(0), chkCafe.isSelected());
        });

        chkAlmoco.setOnAction(event -> {
            salvarEstadoCheckbox("chkAlmoco", chkAlmoco.isSelected());
            atualizarProgresso(dietaAtual.getRefeicoes().get(1), chkAlmoco.isSelected());
        });

        chkLanche.setOnAction(event -> {
            salvarEstadoCheckbox("chkLanche", chkLanche.isSelected());
            atualizarProgresso(dietaAtual.getRefeicoes().get(2), chkLanche.isSelected());
        });

        chkJantar.setOnAction(event -> {
            salvarEstadoCheckbox("chkJantar", chkJantar.isSelected());
            atualizarProgresso(dietaAtual.getRefeicoes().get(3), chkJantar.isSelected());
        });
    }

    private void carregarDieta() {
        dietaAtual = usuario.getDietaAtual();
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
    private void atualizarProgressoGeral() {
        progressoCalorias = 0;
        progressoProteinas = 0;
        progressoCarboidratos = 0;
        progressoGorduras = 0;

        if (chkCafe.isSelected()) {
            atualizarProgresso(dietaAtual.getRefeicoes().get(0), true);
        }
        if (chkAlmoco.isSelected()) {
            atualizarProgresso(dietaAtual.getRefeicoes().get(1), true);
        }
        if (chkLanche.isSelected()) {
            atualizarProgresso(dietaAtual.getRefeicoes().get(2), true);
        }
        if (chkJantar.isSelected()) {
            atualizarProgresso(dietaAtual.getRefeicoes().get(3), true);
        }
    }


    private String formatarNumero(double numero) {
        return String.format("%.2f", numero).replace(".", ",");
    }
}
