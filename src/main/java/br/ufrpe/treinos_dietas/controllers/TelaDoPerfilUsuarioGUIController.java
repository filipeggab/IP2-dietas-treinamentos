package br.ufrpe.treinos_dietas.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;



public class TelaDoPerfilUsuarioGUIController {
    @FXML
    private Button btnVoltarTelaPrincipal;

    @FXML
    public void btnVoltarTelaPrincipalActionPerformed() throws IOException {
        TelaDoTreinoDaSemanaGUIController.VoltarParaTelaPrincipalDoUsuario(btnVoltarTelaPrincipal );
    }
}

