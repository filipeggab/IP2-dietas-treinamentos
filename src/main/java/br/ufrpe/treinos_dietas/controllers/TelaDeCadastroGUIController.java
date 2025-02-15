package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDeCadastroGUIController {


    @FXML
    private Button btnIrParaTelaPrincipal;

    @FXML
    private Button btnVoltarParaTelaDeLogin;


    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfirmeASenha;

    @FXML
    private Label lblErroSenha;


    @FXML
    void btnIrParaTelaDeSelecaoDeFocoActionPerformed() throws IOException {
        if(!this.cadastrarUsuario()){
            return;
        };
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDeSelecaoDeFoco.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void btnVoltarParaTelaDeLoginActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDeLogin.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVoltarParaTelaDeLogin.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public boolean cadastrarUsuario() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String nome = txtNome.getText();
        String confirmeASenha = txtConfirmeASenha.getText();

        if (!senha.equals(confirmeASenha)) {
            txtConfirmeASenha.setText("");
            lblErroSenha.setText("As senhas não coincidem!");
            return false;
        }

        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        lblErroSenha.setText("");
        return true;
    }
}
