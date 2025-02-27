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
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreinoDoDiaGUIController {
    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoTreinoDaSemana.fxml"));
    Parent root = loader.load();
    TelaDoTreinoDaSemanaGUIController controller = loader.getController();

    Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();

    @FXML
    private Button btnVoltaTelaPrincipal;

    @FXML
    private List<Label> mobilidadesLabels;
    @FXML
    private List<Label> cardioLabel;
    @FXML
    private List<Label> treinoForcaLabels;

    @FXML
    private VBox vboxA, vboxB, vboxC;

    public TreinoDoDiaGUIController() throws IOException {
    }

    //Metodo para o botão btnVoltaTelaPrincipal ir para a tela principal
    @FXML
    public void btnVoltaTelaPrincipalActionPerformed() throws IOException {

        this.controller.alocadorLabelsTreinos();

        Stage stage = (Stage) btnVoltaTelaPrincipal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void criarListaDeLabels(){

        mobilidadesLabels = getLabelsVbox(vboxA);
        cardioLabel = getLabelsVbox(vboxB);
        treinoForcaLabels = getLabelsVbox(vboxC);
    }

    public List<Label> getLabelsVbox(VBox vbox) {
        List<Label> labels = new ArrayList<>();
        for (Node node : vbox.getChildren()) {
            if (node instanceof HBox) {
                Label atual = (Label)((HBox)node).getChildren().getFirst();
                if (atual != null && !atual.getText().equals("Mobilidades:") && !atual.getText().equals("Cardiorrespirátorio:") && !atual.getText().equals("Treino de Força:"))
                {
                    labels.add(atual);
                }
            }
        }
        return labels;
    }
    public void labelsEmBranco(List<Label> listaDeLabels){
        for (Label label : listaDeLabels) {
            label.setText(" ");
        }
    }

    public void alocarExerciciosTreino(int i){
        criarListaDeLabels();
        labelsEmBranco(mobilidadesLabels);
        labelsEmBranco(cardioLabel);
        labelsEmBranco(treinoForcaLabels);


        PlanoDeTreino planoAtual = usuario.getPlanoDeTreinoAtual();
        List<Treino> treinosAtuais = planoAtual.getTreinoList();

        Treino treino = treinosAtuais.get(i);
        List<ExercicioPratico> exerciciosTreino = treino.getExercicioList();
        List<ExercicioPratico> mobilidadesTreino = exerciciosTreino.subList(0, 3);
        List<ExercicioPratico> cardioTreino = exerciciosTreino.subList(3, 4);
        List<ExercicioPratico> treinoForcaTreino = exerciciosTreino.subList(4, exerciciosTreino.size());

        if(i == 2 && planoAtual.getNome().equals("Plano para RESISTÊNCIA")){
            mobilidadesTreino = exerciciosTreino.subList(0,4);
            cardioTreino = exerciciosTreino.subList(4, 5);
            treinoForcaTreino = exerciciosTreino.subList(5, exerciciosTreino.size());
        }
        this.controller.alocarExerciciosNasLabels(mobilidadesLabels, mobilidadesTreino);
        this.controller.alocarExerciciosNasLabels(cardioLabel, cardioTreino);
        this.controller.alocarExerciciosNasLabels(treinoForcaLabels, treinoForcaTreino);
    }

    public void alocarExerciciosFlex(int i){
        criarListaDeLabels();
        labelsEmBranco(mobilidadesLabels);
        labelsEmBranco(cardioLabel);
        labelsEmBranco(treinoForcaLabels);

        PlanoDeTreino planoAtual = usuario.getPlanoDeTreinoAtual();
        List<Treino> treinosAtuais = planoAtual.getTreinoList();

        Treino treino = treinosAtuais.get(i);
        List<ExercicioPratico> exerciciosTreino = treino.getExercicioList();
        List<ExercicioPratico> mobilidadesTreino = exerciciosTreino.subList(0, 6);
        List<ExercicioPratico> cardioTreino = exerciciosTreino.subList(6,7);
        List<ExercicioPratico> treinoForcaTreino = exerciciosTreino.subList(7, exerciciosTreino.size());

        if(i == 2){
            mobilidadesTreino = exerciciosTreino.subList(0,8);
            cardioTreino = exerciciosTreino.subList(8,9);
            treinoForcaTreino = exerciciosTreino.subList(9, exerciciosTreino.size());
        }



        this.controller.alocarExerciciosNasLabels(mobilidadesLabels, mobilidadesTreino);
        this.controller.alocarExerciciosNasLabels(cardioLabel, cardioTreino);
        this.controller.alocarExerciciosNasLabels(treinoForcaLabels, treinoForcaTreino);
    }

}
