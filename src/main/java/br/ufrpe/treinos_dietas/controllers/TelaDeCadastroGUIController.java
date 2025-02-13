package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDeCadastroGUIController {
    @FXML
    private Button btnIrParaTelaPrincipal;

    @FXML
    private Button btnVoltarParaTelaDeLogin;

    @FXML
    void btnIrParaTelaPrincipalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuário.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void btnVoltarParaTelaDeLoginActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDeLogin.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
