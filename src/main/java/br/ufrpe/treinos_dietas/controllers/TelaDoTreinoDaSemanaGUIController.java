package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDoTreinoDaSemanaGUIController {

    @FXML
    private Button btnVoltaTelaPrincipal;

    @FXML
    private Button btnTreinoA;

    @FXML
    private Button btnTreinoB;

    @FXML
    private Button btnTreinoC;

    //Metodo para o botão btnVoltaTelaPrincipal ir para a tela principal
    @FXML
    public void btnVoltaTelaPrincipalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuário.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVoltaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Metodo para abrir treino A
    @FXML
    public void btnTreinoAActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TreinoDoDia.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnTreinoA.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Metodo para abrir treino B
    @FXML
    public void btnTreinoBActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TreinoDoDia.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnTreinoB.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Metodo para abrir treino C
    @FXML
    public void btnTreinoCActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TreinoDoDia.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnTreinoC.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
