package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.IOException;


public class TelaPrincipalDoUsuárioGUIController {

    @FXML
    private Button btnDietaSemanal;

    @FXML
    private Button btnTreinoSemanal;

    //Metodo para abrir Dieta Semanal
    @FXML
    public void btnDietaSemanalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDaDieta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnDietaSemanal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Metodo para abrir Treino Semanal
    @FXML
    public void btnTreinoSemanalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoTreinoDaSemana.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnTreinoSemanal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
