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
import java.util.List;


public class TelaPrincipalDoUsuárioGUIController {
    Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();
    private static TelaPrincipalDoUsuárioGUIController instancia;

    public static TelaPrincipalDoUsuárioGUIController getInstance(){
        if(instancia == null){
            instancia = new TelaPrincipalDoUsuárioGUIController();
        }
        return instancia;
    }

     @FXML
    private Label lblBemVindo;

    @FXML
    private Button btnDietaSemanal;

    @FXML
    private Button btnTreinoSemanal;

    @FXML
    private Button btnPerfil;
    // do para abrir Dieta Semanal

    @FXML
    private CheckBox chkCafeManha;

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
    private CheckBox chkTreinoDeForca;

    @FXML
    private Label label1,label2,label3;

    public void atualizarLabels(){

        PlanoDeTreino planoAtual = repositorioPlanoDeTreino.retornarPlanos();
        List<Treino> treinosAtuais = planoAtual.getTreinoList();
        Treino treinoA = treinosAtuais.get(0);
        Treino treinoB = treinosAtuais.get(1);
        Treino treinoC = treinosAtuais.get(2);

        List<ExercicioPratico> exerciciosTreinoA = treinoA.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoB = treinoB.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoC = treinoC.getExercicioList();
        /*
        selector
                if(contador == 0){
                    selector = 0
                }
                else{
                    selector = contador * 3
                }

        contador % 3 = 1 && planoAtual.getDataInicial().plusDays(selector) == treinoB ****** fazer um if-else
                0 1 2
                3 4 5
                6 7 8
        switch(String treino){
        int selector = selectorGenerator();
        */

        label1.setText(exerciciosTreinoA.get(0).toString());
        label2.setText(exerciciosTreinoA.get(1).toString());

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
