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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class TelaPrincipalDoUsuarioGUIController {
    Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();
    LocalDate dataAtualizada = LocalDate.now();
    LocalDate dataAdicionada = LocalDate.now();



    @FXML
    private Button btnDietaSemanal;

    @FXML
    private Button btnTreinoSemanal;

    @FXML
    private Button btnPerfil;

    @FXML
    private CheckBox chkCafeDaManha;

    @FXML
    private CheckBox chkAlmoco;

    @FXML
    private CheckBox chkLanche;

    @FXML
    private CheckBox chkJantar;

    @FXML
    private CheckBox chkMobilidade;

    @FXML
    private CheckBox chkCardio;

    @FXML
    private CheckBox chkForca;

    @FXML
    private Label labelM1,labelM2,labelM3, labelC1, labelF1, labelF2, labelF3,labelF4,labelF5,labelF6;

    @FXML
    private Label labelData;


    public void atualizarLabels(){
        checarCheckBoxes();
        labelsEmBranco();

        labelData.setText("Dia " + dataAtualizada.toString());

        PlanoDeTreino planoAtual = repositorioPlanoDeTreino.retornarPlanos();
        List<Treino> treinosAtuais = planoAtual.getTreinoList();
        Treino treinoA = treinosAtuais.get(0);
        Treino treinoB = treinosAtuais.get(1);
        Treino treinoC = treinosAtuais.get(2);

        List<ExercicioPratico> exerciciosTreinoA = treinoA.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoB = treinoB.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoC = treinoC.getExercicioList();
        int selector = checarData();

        switch (selector){
            case 0: //TREINO A
                if(planoAtual.getNome().equalsIgnoreCase("Plano para FLEXIBILIDADE")){
                    alocadorFlexibilidade(exerciciosTreinoA);
                }
                else{
                    alocador(exerciciosTreinoA);
                }
                break;
            case 1: //TREINO B
                if(planoAtual.getNome().equalsIgnoreCase("Plano para FLEXIBILIDADE")) {
                    alocadorFlexibilidade(exerciciosTreinoB);
                    labelF4.setText(exerciciosTreinoB.get(10).toString());
                }
                else{
                    alocador(exerciciosTreinoB);
                }
                break;
            case 2: //TREINO C
                if(planoAtual.getNome().equalsIgnoreCase("Plano para FLEXIBILIDADE")) {
                    alocadorFlexibilidade(exerciciosTreinoC);
                    labelC1.setText(exerciciosTreinoC.get(8).toString());
                    labelF1.setText(exerciciosTreinoC.get(9).toString());
                    labelF2.setText(exerciciosTreinoC.get(10).toString());
                    labelF3.setText(" ");
                }
                else{
                    alocador(exerciciosTreinoC);
                }
                break;

            default:
                break;
        }
    }

    private void alocadorFlexibilidade(List<ExercicioPratico> exerciciosTreinoB) {
        labelM1.setText(exerciciosTreinoB.get(0).toString());
        labelM2.setText(exerciciosTreinoB.get(1).toString());
        labelM3.setText(exerciciosTreinoB.get(2).toString());
        labelC1.setText(exerciciosTreinoB.get(6).toString());
        labelF1.setText(exerciciosTreinoB.get(7).toString());
        labelF2.setText(exerciciosTreinoB.get(8).toString());
        labelF3.setText(exerciciosTreinoB.get(9).toString());
    }

    private void alocador(List<ExercicioPratico> exerciciosTreinoA) {
        labelM1.setText(exerciciosTreinoA.get(0).toString());
        labelM2.setText(exerciciosTreinoA.get(1).toString());
        labelM3.setText(exerciciosTreinoA.get(2).toString());
        labelC1.setText(exerciciosTreinoA.get(3).toString());
        labelF1.setText(exerciciosTreinoA.get(4).toString());
        labelF2.setText(exerciciosTreinoA.get(5).toString());
        labelF3.setText(exerciciosTreinoA.get(6).toString());
        labelF4.setText(exerciciosTreinoA.get(7).toString());
        labelF5.setText(exerciciosTreinoA.get(8).toString());
        labelF6.setText(exerciciosTreinoA.get(9).toString());
    }


    public void checarCheckBoxes(){
        dataAtualizada = LocalDate.now();

        if(chkMobilidade.isSelected() && chkCardio.isSelected() && chkForca.isSelected()){
            if(dataAtualizada.isBefore(dataAdicionada)){
            usuario.acrescentarContador();
            dataAdicionada = LocalDate.now();
            }
        }
    }

    public int checarData(){
        int selector;
        selector = usuario.getContador() % 3;

        return selector;
    }

    public void labelsEmBranco(){
        labelM1.setText(" ");
        labelM2.setText(" ");
        labelM3.setText(" ");
        labelC1.setText(" ");
        labelF1.setText(" ");
        labelF2.setText(" ");
        labelF3.setText(" ");
        labelF4.setText(" ");
        labelF5.setText(" ");
        labelF6.setText(" ");

    }

    @FXML
    public void btnDietaSemanalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDaDieta.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnDietaSemanal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void btnTreinoSemanalActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoTreinoDaSemana.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnTreinoSemanal.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void btnPerfilActionPerformed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoPerfil.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnPerfil.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
