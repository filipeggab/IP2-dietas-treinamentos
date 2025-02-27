package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
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
    private List<Label> dietaCafeDaManha;
    @FXML
    private List<Label> almoco;
    @FXML
    private List<Label> lanche;
    @FXML
    private List<Label> jantar;
    @FXML
    private VBox vboxA, vboxB, vboxC, vboxD;

    public List<Label> getLabelsRefeicao(VBox vbox) {
        List<Label> labels = new ArrayList<>();
        for (Node node : vbox.getChildren()) {
            if (node instanceof Label) {
                String texto = ((Label) node).getText();
                if (!texto.equals("Café da manhã") && !texto.equals("Almoço") && !texto.equals("Lanche") && !texto.equals("Jantar")) {
                    labels.add((Label) node);
                }
            }
        }
        return labels;
    }

    @FXML
    public void btnVoltarTelaPrincipalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuario.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVoltarTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
