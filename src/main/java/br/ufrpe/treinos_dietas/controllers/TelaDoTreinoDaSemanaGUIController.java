package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;

import br.ufrpe.treinos_dietas.dados.RepositorioPlanoDeTreino;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.ExercicioPratico;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Treino;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaDoTreinoDaSemanaGUIController {
    Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();

    @FXML
    private Button btnVoltaTelaPrincipal;

    @FXML
    private Button btnTreinoA;

    @FXML
    private Button btnTreinoB;

    @FXML
    private Button btnTreinoC;

    @FXML
    private Button btnIrParaCalendarioDeTreino;

    @FXML
    private List<Label> treinoALabels;
    @FXML
    private List<Label> treinoBLabels;
    @FXML
    private List<Label> treinoCLabels;

    private List<Label> getLabelsVbox(VBox vbox) {
        List<Label> labels = new ArrayList<>();
        for (Node node : vbox.getChildren()) {
            if (node instanceof Label && !((Label)node).getText().equals("Treino A") && !((Label)node).getText().equals("Treino B") && !((Label)node).getText().equals("Treino C")) {
                labels.add((Label) node);
            }
        }
        return labels;
    }

    private void alocadorLabelsTreinos(List<Label> treinoALabels, List<Label> treinoBLabels, List<Label> treinoCLabels) {
        PlanoDeTreino planoAtual = repositorioPlanoDeTreino.retornarPlanos();
        List<Treino> treinosAtuais = planoAtual.getTreinoList();

        Treino treinoA = treinosAtuais.get(0);
        Treino treinoB = treinosAtuais.get(1);
        Treino treinoC = treinosAtuais.get(2);

        List<ExercicioPratico> exerciciosTreinoA = treinoA.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoB = treinoB.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoC = treinoC.getExercicioList();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < treinoALabels.size(); j++) {

            }

        }

    }


    //Metodo para o botão btnVoltaTelaPrincipal ir para a tela principal
    @FXML
    public void btnVoltaTelaPrincipalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuario.fxml"));
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
    @FXML
    public void btnIrParaCalendarioDeTreinoActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/CalendarioDeTreinos.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrParaCalendarioDeTreino.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
