package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Metrica;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumSexo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

public class TelaEdicaoPerfilGUIController {

    @FXML
    private TextField txtNome, txtEmail, txtAltura, txtPeso;
    @FXML
    private DatePicker dpDataDeNascimento;
    @FXML
    private ComboBox<EnumSexo> cbSexo;
    @FXML
    private Button btnSalvar, btnCancelar;

    private Usuario usuario;
    private Metrica ultimaMetrica;

    @FXML
    public void initialize() {
        // Obtém o usuário logado na sessão
        usuario = SessaoUsuario.getInstancia().getUsuario();

        if (usuario != null) {
            // Preenchendo os campos com os dados existentes do usuário
            txtNome.setText(usuario.getNome() != null ? usuario.getNome() : "");
            txtEmail.setText(usuario.getEmail() != null ? usuario.getEmail() : "");

            if (usuario.getDataNasc() != null) {
                dpDataDeNascimento.setValue(usuario.getDataNasc());
            }

            // Preenchendo o ComboBox com as opções de EnumSexo
            cbSexo.getItems().setAll(EnumSexo.values());
            cbSexo.setValue(usuario.getSexo() != null ? usuario.getSexo() : EnumSexo.MASCULINO);

            // Obtém a última métrica do usuário
            ultimaMetrica = usuario.ultimaMetrica();
            if (ultimaMetrica != null) {
                txtAltura.setText(String.valueOf(ultimaMetrica.getAltura()));
                txtPeso.setText(String.valueOf(ultimaMetrica.getPeso()));
            }
        }
    }

    @FXML
    public void btnSalvarActionPerformed() throws IOException {
        if (usuario != null) {
            // Atualiza os dados básicos do usuário
            if (!txtNome.getText().isEmpty()) {
                usuario.setNome(txtNome.getText());
            }
            if (!txtEmail.getText().isEmpty()) {
                usuario.setEmail(txtEmail.getText());
            }
            if (dpDataDeNascimento.getValue() != null) {
                usuario.setDataNasc(dpDataDeNascimento.getValue());
            }
            if (cbSexo.getValue() != null) {
                usuario.setSexo(cbSexo.getValue());
            }

            // Verifica se os valores de altura e peso foram alterados antes de criar uma nova métrica
            try {
                int novaAltura = Integer.parseInt(txtAltura.getText());
                double novoPeso = Double.parseDouble(txtPeso.getText());

                if (ultimaMetrica == null || ultimaMetrica.getAltura() != novaAltura || ultimaMetrica.getPeso() != novoPeso) {
                    // Se não há uma métrica anterior OU os valores foram alterados, cria uma nova métrica
                    Metrica novaMetrica = new Metrica(novaAltura, novoPeso, LocalDate.now());
                    usuario.adicionarMetrica(novaMetrica);
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Altura e Peso devem ser valores numéricos.");
            }
        }

        // Voltar para a tela de perfil
        btnCancelarActionPerformed();
    }

    @FXML
    public void btnCancelarActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoPerfil.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
