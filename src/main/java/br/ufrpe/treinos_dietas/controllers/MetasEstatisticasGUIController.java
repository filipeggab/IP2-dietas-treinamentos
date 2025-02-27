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
        cadastroRefeicao = new CadastroRefeicao();
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

        lblCalorias.setText("0/" + String.format("%.1f", metaCalorias) + " kcal");
        lblProteinas.setText("0/" + String.format("%.1f", metaProteinas) + "g");
        lblCarboidratos.setText("0/" + String.format("%.1f", metaCarboidratos) + "g");
        lblGorduras.setText("0/" + String.format("%.1f", metaGorduras) + "g");

        for (Refeicao refeicao : refeicoes) {
            configurarRefeicao(refeicao);
        }
    }

    private void configurarRefeicao(Refeicao refeicao) {
        String nomeRefeicao = refeicao.getNome().toLowerCase();
        double calorias = refeicao.caloriasTotais();
        double proteinas = refeicao.proteinasTotais();
        double carboidratos = refeicao.carboidratosTotais();
        double gorduras = refeicao.gordurasTotais();
        boolean realizada = refeicao.isRealizada();

        String caloriasFormatadas = String.format("%.2f", calorias).replace(".", ",");
        String proteinasFormatadas = String.format("%.2f", proteinas).replace(".", ",");

        if (nomeRefeicao.contains("café da manhã")) {
            lblCalCafe.setText(caloriasFormatadas + " kcal");
            lblProtCafe.setText(proteinasFormatadas + "g");
            chkCafe.setSelected(realizada);
            chkCafe.setOnAction(event -> atualizarProgresso(refeicao, calorias, proteinas, carboidratos, gorduras));
        } else if (nomeRefeicao.contains("almoço")) {
            lblCalAlmoco.setText(caloriasFormatadas + " kcal");
            lblProtAlmoco.setText(proteinasFormatadas + "g");
            chkAlmoco.setSelected(realizada);
            chkAlmoco.setOnAction(event -> atualizarProgresso(refeicao, calorias, proteinas, carboidratos, gorduras));
        } else if (nomeRefeicao.contains("lanche")) {
            lblCalLanche.setText(caloriasFormatadas + " kcal");
            lblProtLanche.setText(proteinasFormatadas + "g");
            chkLanche.setSelected(realizada);
            chkLanche.setOnAction(event -> atualizarProgresso(refeicao, calorias, proteinas, carboidratos, gorduras));
        } else if (nomeRefeicao.contains("jantar")) {
            lblCalJantar.setText(caloriasFormatadas + " kcal");
            lblProtJantar.setText(proteinasFormatadas + "g");
            chkJantar.setSelected(realizada);
            chkJantar.setOnAction(event -> atualizarProgresso(refeicao, calorias, proteinas, carboidratos, gorduras));
        }
    }

    private void atualizarProgresso(Refeicao refeicao, double calorias, double proteinas, double carboidratos, double gorduras) {
        try {
            // Busca a refeição pelo nome sem acentos
            String nomeFormatado = removerAcentos(refeicao.getNome());
            Refeicao refeicaoAtualizada = buscarRefeicaoNoRepositorio(nomeFormatado);

            if (refeicaoAtualizada == null) {
                System.out.println("Erro ao atualizar refeição: " + refeicao.getNome() + " não encontrada.");
                return;
            }

            boolean realizada = chkCafe.isSelected() || chkAlmoco.isSelected() || chkLanche.isSelected() || chkJantar.isSelected();
            cadastroRefeicao.editarRefeicao(nomeFormatado, null, realizada);

            if (realizada) {
                progressoCalorias += calorias;
                progressoProteinas += proteinas;
                progressoCarboidratos += carboidratos;
                progressoGorduras += gorduras;
            } else {
                progressoCalorias -= calorias;
                progressoProteinas -= proteinas;
                progressoCarboidratos -= carboidratos;
                progressoGorduras -= gorduras;
            }

            atualizarBarrasELabels();
        } catch (RefeicaoNaoCadastradaException e) {
            System.out.println("Erro ao atualizar refeição: " + e.getMessage());
        }
    }

    private Refeicao buscarRefeicaoNoRepositorio(String nome) {
        try {
            List<Refeicao> refeicoes = dietaAtual.getRefeicoes();
            for (Refeicao r : refeicoes) {
                if (removerAcentos(r.getNome()).equalsIgnoreCase(nome)) {
                    return r;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar refeição: " + e.getMessage());
        }
        return null;
    }

    // remover acentos
    private String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .trim();
    }


}
