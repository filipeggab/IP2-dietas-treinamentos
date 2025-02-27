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
    private Button btnIrParaHistoricoTreinosMetricas;

    @FXML
    private Label lblData;

    @FXML
    private List<Label> treinoALabels;
    @FXML
    private List<Label> treinoBLabels;
    @FXML
    private List<Label> treinoCLabels;

    @FXML
    private VBox vboxA, vboxB, vboxC;

    public List<Label> getLabelsVbox(VBox vbox) {
        List<Label> labels = new ArrayList<>();
        for (Node node : vbox.getChildren()) {
            if (node instanceof Label && !((Label)node).getText().equals("Treino A") && !((Label)node).getText().equals("Treino B") && !((Label)node).getText().equals("Treino C")) {
                labels.add((Label) node);
            }
        }
        return labels;
    }



    public void alocadorLabelsTreinos() {
        PlanoDeTreino planoAtual = usuario.getPlanoDeTreinoAtual();
        List<Treino> treinosAtuais = planoAtual.getTreinoList();

        Treino treinoA = treinosAtuais.get(0);
        Treino treinoB = treinosAtuais.get(1);
        Treino treinoC = treinosAtuais.get(2);

        List<ExercicioPratico> exerciciosTreinoA = treinoA.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoB = treinoB.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoC = treinoC.getExercicioList();


        lblData.setText("Semana 1-4");
        treinoALabels = getLabelsVbox(vboxA);
        treinoBLabels = getLabelsVbox(vboxB);
        treinoCLabels = getLabelsVbox(vboxC);


        alocarExerciciosNasLabels(treinoALabels, exerciciosTreinoA);
        alocarExerciciosNasLabels(treinoBLabels, exerciciosTreinoB);
        alocarExerciciosNasLabels(treinoCLabels, exerciciosTreinoC);

    }

    public void alocarExerciciosNasLabels(List<Label> listaLabels, List<ExercicioPratico> listaExercicios) {

        int tamanho = Math.min(listaExercicios.size(), listaLabels.size());
        for (int i = 0; i < tamanho; i++) {
            listaLabels.get(i).setText(listaExercicios.get(i).toString());
        }
    }


    //Metodo para o botão btnVoltaTelaPrincipal ir para a tela principal
    @FXML
    public void btnVoltaTelaPrincipalActionPerformed() throws IOException {
        VoltarParaTelaPrincipalDoUsuario(btnVoltaTelaPrincipal);
    }

    public static void VoltarParaTelaPrincipalDoUsuario(Button btnVoltaTelaPrincipal) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuario.fxml"));
        Parent root = loader.load();

        TelaPrincipalDoUsuarioGUIController controller = loader.getController();
        controller.atualizarLabels();


        Stage stage = (Stage) btnVoltaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Metodo para abrir treino A
    @FXML
    public void btnTreinoAActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TreinoDoDia.fxml"));
        Parent root = loader.load();

        TreinoDoDiaGUIController controller = loader.getController();
        PlanoDeTreino planoAtual = usuario.getPlanoDeTreinoAtual();
        if(planoAtual.getNome().equals("Plano para FLEXIBILIDADE")){
        controller.alocarExerciciosFlex(0);
        }
        else{
            controller.alocarExerciciosTreino(0);
        }

        Stage stage = (Stage) btnTreinoA.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Metodo para abrir treino B
    @FXML
    public void btnTreinoBActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TreinoDoDia.fxml"));
        Parent root = loader.load();

        TreinoDoDiaGUIController controller = loader.getController();
        PlanoDeTreino planoAtual = usuario.getPlanoDeTreinoAtual();
        if(planoAtual.getNome().equals("Plano para FLEXIBILIDADE")){
            controller.alocarExerciciosFlex(1);
        }
        else{
            controller.alocarExerciciosTreino(1);
        }

        Stage stage = (Stage) btnTreinoB.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Metodo para abrir treino C
    @FXML
    public void btnTreinoCActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TreinoDoDia.fxml"));
        Parent root = loader.load();

        TreinoDoDiaGUIController controller = loader.getController();
        PlanoDeTreino planoAtual = usuario.getPlanoDeTreinoAtual();
        if(planoAtual.getNome().equals("Plano para FLEXIBILIDADE")){
            controller.alocarExerciciosFlex(2);
        }
        else{
            controller.alocarExerciciosTreino(2);
        }


        Stage stage = (Stage) btnTreinoC.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void btnIrParaHistoricoTreinosMetricasActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/HistoricoDeTreinosMetricas.fxml"));
        Parent root = loader.load();

        TelaDeHistoricoTreinosMetricasGUIController controller = loader.getController();
        controller.alocadorLabels(0);

        Stage stage = (Stage) btnIrParaHistoricoTreinosMetricas.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
