package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.exceptions.SenhasDiferentesException;
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

    private Usuario usuario;

    @FXML
    private Button btnIrParaTelaPrincipal;

    @FXML
    private Button btnVoltarParaTelaDeLogin;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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
    void btnIrParaTelaPrincipalActionPerformed() throws IOException, SenhasDiferentesException {
        if(!this.cadastrarUsuario()){
            return;
        };
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

    public boolean cadastrarUsuario() throws SenhasDiferentesException {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String nome = txtNome.getText();
        String confirmeASenha = txtConfirmeASenha.getText();

        if (!senha.equals(confirmeASenha)) {
            txtConfirmeASenha.setText(""); // Limpa o campo de confirmação
            lblErroSenha.setText("As senhas não coincidem!"); // Exibe a mensagem de erro (Adicione um Label no FXML)
            return false; // Sai do método sem cadastrar
        }

        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        lblErroSenha.setText("");
        return true;
    }
}
