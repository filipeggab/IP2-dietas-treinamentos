package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDeLoginGUIController {
    @FXML
    private Button btnIrParaTelaPrincipal;

    @FXML
    private Button btnIrParaTelaDeCadastro;

    @FXML
    public void btnIrParaTelaPrincipalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuário.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void btnIrParaTelaDeCadastrorActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDeCadastro.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaDeCadastro.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
