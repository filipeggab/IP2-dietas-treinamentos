package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.dados.RepositorioUsuarios;
import br.ufrpe.treinos_dietas.exceptions.UsuarioNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TelaDeLoginGUIController {
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    @FXML
    private Button btnIrParaTelaPrincipal;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Label lblErroLogin;

    @FXML
    private Button btnIrParaTelaDeCadastro;


    @FXML
    public void btnIrParaTelaPrincipalActionPerformed() throws IOException, UsuarioNaoCadastradoException {
        try{
            login();
            TelaDoTreinoDaSemanaGUIController.VoltarParaTelaPrincipalDoUsuario(btnIrParaTelaPrincipal);
        }catch(UsuarioNaoCadastradoException | NullPointerException | NumberFormatException e){
            lblErroLogin.setText(e.getMessage());
        }

    }

    @FXML
    public void btnIrParaTelaDeCadastroActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDeCadastro.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaTelaDeCadastro.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void login() throws UsuarioNaoCadastradoException {
        try {
            List<Usuario> listaDeUsuarios = repositorioUsuarios.getUsuarios();
            if (listaDeUsuarios == null || listaDeUsuarios.isEmpty()) {
                throw new UsuarioNaoCadastradoException("Nenhum usuário cadastrado!");
            }
            System.out.println("Quantidade de usuários carregados: " + listaDeUsuarios.size());

            for (Usuario usuario : listaDeUsuarios) {
                if (usuario.getEmail().equals(txtNome.getText()) && usuario.getSenha().equals(txtSenha.getText())) {
                    SessaoUsuario.getInstancia().setUsuario(usuario);
                    return;
                }
            }
            throw new UsuarioNaoCadastradoException("Email ou senha incorretos");
        } catch (UsuarioNaoCadastradoException | NullPointerException e) {
            lblErroLogin.setText("Email ou senha incorretos");
            throw e;
        }
    }


}
