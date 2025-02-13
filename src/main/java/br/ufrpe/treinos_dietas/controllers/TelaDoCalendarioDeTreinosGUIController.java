package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDoCalendarioDeTreinosGUIController {
    @FXML
    private Button btnVoltarParaTreinoDaSemana;

    @FXML
    public void btnVoltarParaTreinoDaSemanaActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoTreinoDaSemana.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVoltarParaTreinoDaSemana.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

