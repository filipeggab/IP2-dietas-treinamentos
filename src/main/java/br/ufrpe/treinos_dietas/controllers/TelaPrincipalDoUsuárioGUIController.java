package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class TelaPrincipalDoUsuárioGUIController {
    private Usuario usuario;
    @FXML
    private Label lblBemVindo;

    @FXML
    private Button btnDietaSemanal;

    @FXML
    private Button btnTreinoSemanal;

    @FXML
    private Button btnPerfil;
    // do para abrir Dieta Semanal

    @FXML
    private CheckBox chkCafeManha;

    @FXML
    private CheckBox chkAlmoco;

    @FXML
    private CheckBox chkLanche;

    @FXML
    private CheckBox chkJantar;

    @FXML
    private CheckBox chkMobilidade;

    @FXML
    private CheckBox chkCardio;

    @FXML
    private CheckBox chkTreinoDeForca;

    @FXML
    public void btnDietaSemanalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDaDieta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnDietaSemanal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void btnTreinoSemanalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoTreinoDaSemana.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnTreinoSemanal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void btnPerfilActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoPerfil.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnPerfil.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
