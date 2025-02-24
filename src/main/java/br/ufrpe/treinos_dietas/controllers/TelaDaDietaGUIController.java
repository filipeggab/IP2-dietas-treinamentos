package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class TelaDaDietaGUIController {

    @FXML
    private Button btnVoltarTelaPrincipal;

    @FXML
    public void btnVoltarTelaPrincipalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuario.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVoltarTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
