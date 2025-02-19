package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDeLoginGUIController {
    private Usuario usuario;
    @FXML
    private Button btnIrParaTelaPrincipal;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private CheckBox chkLembrarDeMim;

    @FXML
    private Button btnIrParaTelaDeCadastro;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @FXML
    public void btnIrParaTelaPrincipalActionPerformed() throws IOException {
        this.login();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuário.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void btnIrParaTelaDeCadastroActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDeCadastro.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaDeCadastro.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void login () throws IOException{
        String email = txtNome.getText();
        String senha = txtSenha.getText();

        usuario.setEmail(email);
        usuario.setSenha(senha);
    }
}
