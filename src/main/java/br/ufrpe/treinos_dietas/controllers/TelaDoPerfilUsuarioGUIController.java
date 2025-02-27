package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Metrica;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;



public class TelaDoPerfilUsuarioGUIController {
    @FXML
    private Label lblNome, lblEmail, lblDataNascimento, lblIdade, lblSexo;
    @FXML
    private Label lblDataMetrica, lblAltura, lblPeso, lblIMC;
    @FXML
    private Label lblDiferencaPeso, lblIMCIDeal;
    @FXML
    private Button btnVoltarTelaPrincipal, btnEditarPerfil;
    @FXML
    private ProgressBar progressBarIMC, progressBarPeso;

    @FXML
    public void btnVoltarTelaPrincipalActionPerformed() throws IOException {
        TelaDoTreinoDaSemanaGUIController.VoltarParaTelaPrincipalDoUsuario(btnVoltarTelaPrincipal );
    }

    @FXML
    public void btnEditarPerfilActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaEdicaoPerfil.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVoltarTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void initialize() {
        carregarDadosUsuario();
    }

    @FXML
    public void carregarDadosUsuario() {
        // Obtendo o usuário logado
        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();

        if (usuario != null) {
            lblNome.setText(usuario.getNome() != null ? usuario.getNome() : "-");

            lblEmail.setText(
                    usuario.getEmail() != null && !usuario.getEmail().isEmpty() ? usuario.getEmail() : "-"
            );

            lblDataNascimento.setText(usuario.getDataNasc() != null ? usuario.getDataNasc().toString() : "-");

            lblIdade.setText(usuario.getDataNasc() != null ? String.valueOf(usuario.calcularIdade()) : "-");

            lblSexo.setText(usuario.getSexo() != null ? usuario.getSexo().toString() : "-");

            Metrica ultimaMetrica = usuario.ultimaMetrica();
            if (ultimaMetrica != null) {
                double alturaEmMetros = ultimaMetrica.getAltura() / 100.0; // Converter cm para metros
                double pesoAtual = ultimaMetrica.getPeso();
                double pesoIdeal = 22 * (alturaEmMetros * alturaEmMetros);
                double diferencaPeso = pesoIdeal - pesoAtual;
                lblIMCIDeal.setText("IMC ideal: 22.0");
                lblDiferencaPeso.setText(String.format("%+.2f kg", diferencaPeso));
                lblDataMetrica.setText(ultimaMetrica.getDataDaMetrica().toString());
                lblAltura.setText(ultimaMetrica.getAltura() + " cm");
                lblPeso.setText(ultimaMetrica.getPeso() + " kg");
                lblIMC.setText(String.format("%.2f", ultimaMetrica.calcularIMC()));
                double imc = ultimaMetrica.calcularIMC();
                double progresso = calcularProgresso(imc);
                progressBarIMC.setProgress(progresso);
                double progressoPeso = calcularProgressoPeso(pesoAtual, pesoIdeal);
                progressBarPeso.setProgress(progressoPeso);

            } else {
                lblDataMetrica.setText("-");
                lblAltura.setText("-");
                lblPeso.setText("-");
                lblIMC.setText("-");
            }

        }
        else {
            lblNome.setText("-");
            lblEmail.setText("-");
            lblDataNascimento.setText("-");
            lblIdade.setText("-");
            lblSexo.setText("-");
            lblDataMetrica.setText("-");
            lblAltura.setText("-");
            lblPeso.setText("-");
            lblIMC.setText("-");

        }
    }
    private double calcularProgresso(double imc) {
        double metaIMC = 22.0;
        double diferenca = Math.abs(imc - metaIMC);
        double maxDiferenca = 10.0;

        double progresso = 1.0 - (diferenca / maxDiferenca);
        return Math.max(0, Math.min(1, progresso));
    }
    private double calcularProgressoPeso(double pesoAtual, double pesoIdeal) {
        double diferenca = Math.abs(pesoAtual - pesoIdeal);
        double maxDiferenca = 20.0; // Define um limite para escala

        double progresso = 1.0 - (diferenca / maxDiferenca);
        return Math.max(0, Math.min(1, progresso)); // Garante que fique entre 0 e 1
    }


}

