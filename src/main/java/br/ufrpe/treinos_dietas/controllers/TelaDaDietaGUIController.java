package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TelaDaDietaGUIController {

    @FXML
    private Button btnVoltarTelaPrincipal;
    @FXML
    private Label lblData;

    @FXML
    private Button btnAcompanharMetas;


    @FXML
    private List<Label> dietaCafeDaManha;
    @FXML
    private List<Label> almoco;
    @FXML
    private List<Label> lanche;
    @FXML
    private List<Label> jantar;
    @FXML
    private VBox vboxCafe, vboxAlmoco, vboxLanche, vboxJantar;

    private Usuario usuario = SessaoUsuario.getInstancia().getUsuario();

    private List<Label> cafeLabels;
    private List<Label> almocoLabels;
    private List<Label> lancheLabels;
    private List<Label> jantarLabels;
    public List<Label> getLabelsRefeicao(VBox vbox) {
        List<Label> labels = new ArrayList<>();
        for (Node node : vbox.getChildren()) {
            if (node instanceof Label) {
                String texto = ((Label) node).getText();
                if (!texto.equals("Café da manhã") && !texto.equals("Almoço") && !texto.equals("Lanche da tarde") && !texto.equals("Jantar")) {
                    labels.add((Label) node);
                }
            }
        }
        return labels;
    }

    public void alocadorLabelsDietas() {
        Dieta dietatual = usuario.getDietaAtual();
        List<Refeicao> refeicoesAtuais = dietatual.getRefeicoes();

        Refeicao cafeDaManha = refeicoesAtuais.get(0);
        Refeicao almoco = refeicoesAtuais.get(1);
        Refeicao lanche = refeicoesAtuais.get(2);
        Refeicao jantar = refeicoesAtuais.get(3);

        List<Comida> alimentosCafe = cafeDaManha.getComidas();
        List<Comida> alimentosAlmoco = almoco.getComidas();
        List<Comida> alimentosLanche = lanche.getComidas();
        List<Comida> alimentosJantar = jantar.getComidas();

        lblData.setText("Plano Alimentar");

        cafeLabels = getLabelsRefeicao(vboxCafe);
        almocoLabels = getLabelsRefeicao(vboxAlmoco);
        lancheLabels = getLabelsRefeicao(vboxLanche);
        jantarLabels = getLabelsRefeicao(vboxJantar);
        labelsEmBranco(cafeLabels);
        labelsEmBranco(almocoLabels);
        labelsEmBranco(jantarLabels);
        labelsEmBranco(lancheLabels);
        alocarAlimentosNasLabels(cafeLabels, alimentosCafe);
        alocarAlimentosNasLabels(almocoLabels, alimentosAlmoco);
        alocarAlimentosNasLabels(lancheLabels, alimentosLanche);
        alocarAlimentosNasLabels(jantarLabels, alimentosJantar);
    }


    public void alocarAlimentosNasLabels(List<Label> listaLabels, List<Comida> listaComida) {

        int tamanho = Math.min(listaComida.size(), listaLabels.size());
        for (int i = 0; i < tamanho; i++) {
            listaLabels.get(i).setText(listaComida.get(i).toString());
        }
    }
public void labelsEmBranco(List<Label> lista){
        for(Label labels : lista){
            labels.setText(" ");
        }
}

    @FXML
    public void btnVoltarTelaPrincipalActionPerformed() throws IOException {
        TelaDoTreinoDaSemanaGUIController.VoltarParaTelaPrincipalDoUsuario(btnVoltarTelaPrincipal);
    }

    @FXML
    public void btnAcompanharMetasActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/MetasEstatisticas.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnAcompanharMetas.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
