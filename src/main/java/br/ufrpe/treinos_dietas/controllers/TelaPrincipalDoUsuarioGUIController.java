package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.dados.RepositorioDietas;
import br.ufrpe.treinos_dietas.dados.RepositorioPlanoDeTreino;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Comida;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;
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
    private Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
    private RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();
    private RepositorioDietas repositorioDietas =  RepositorioDietas.getInstance();
    private static LocalDate dataAtualizada = LocalDate.now();
    private static LocalDate dataAdicionada = dataAtualizada.minusDays(1);



    @FXML
    private Button btnDietaSemanal;

    @FXML
    private Button btnTreinoSemanal;

    @FXML
    private Button btnPerfil;

    @FXML
    private CheckBox chkMobilidade;

    @FXML
    private CheckBox chkCardio;

    @FXML
    private CheckBox chkForca;

    @FXML
    private Label labelM1,labelM2,labelM3, labelC1, labelF1, labelF2, labelF3,labelF4,labelF5,labelF6;

    @FXML
    private Label lblCafe1, lblCafe2, lblAlmoco1, lblAlmoco2, lblLanche1, lblLanche2, lblLanche3, lblJantar1, lblJantar2, lblJantar3;

    @FXML
    private Label labelData;


    public void atualizarLabels(){
        labelsEmBranco();

        labelData.setText("Dia " + dataAtualizada.toString());
        System.out.println(usuario);

        PlanoDeTreino planoAtual = usuario.getPlanoDeTreinoAtual();
        if (planoAtual == null) {
            System.out.println("Erro: planoAtual está nulo!");
            return;
        }
        List<Treino> treinosAtuais = planoAtual.getTreinoList();
        Treino treinoA = treinosAtuais.get(0);
        Treino treinoB = treinosAtuais.get(1);
        Treino treinoC = treinosAtuais.get(2);

        List<ExercicioPratico> exerciciosTreinoA = treinoA.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoB = treinoB.getExercicioList();
        List<ExercicioPratico> exerciciosTreinoC = treinoC.getExercicioList();
        int selector = checarData();

        Dieta dietaAtual = usuario.getDietaAtual();
        List<Refeicao> listaDeRefeicoes= dietaAtual.getRefeicoes();
        alocadorCafeDaManhaEAlmoco(listaDeRefeicoes.get(0).getComidas(), listaDeRefeicoes.get(1).getComidas());
        alocadorLancheEJanta(listaDeRefeicoes.get(2).getComidas(), listaDeRefeicoes.get(3).getComidas());
        switch (selector){
            case 0: //TREINO A
                if(planoAtual.getNome().equalsIgnoreCase("Plano para FLEXIBILIDADE")){
                    alocadorFlexibilidade(exerciciosTreinoA);
                }
                else if(planoAtual.getNome().equalsIgnoreCase("Plano para FORÇA_MUSCULAR")){
                    alocadorForca(exerciciosTreinoA);
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
                else if(planoAtual.getNome().equalsIgnoreCase("Plano para FORÇA_MUSCULAR")){
                    alocadorForca(exerciciosTreinoB);
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
                else if(planoAtual.getNome().equalsIgnoreCase("Plano para FORÇA_MUSCULAR")){
                    alocadorForca(exerciciosTreinoC);
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
        alocadorForca(exerciciosTreinoA);
        labelF5.setText(exerciciosTreinoA.get(8).toString());
        labelF6.setText(exerciciosTreinoA.get(9).toString());
    }
    private void alocadorForca(List<ExercicioPratico> exerciciosTreinoForca){
        labelM1.setText(exerciciosTreinoForca.get(0).toString());
        labelM2.setText(exerciciosTreinoForca.get(1).toString());
        labelM3.setText(exerciciosTreinoForca.get(2).toString());
        labelC1.setText(exerciciosTreinoForca.get(3).toString());
        labelF1.setText(exerciciosTreinoForca.get(4).toString());
        labelF2.setText(exerciciosTreinoForca.get(5).toString());
        labelF3.setText(exerciciosTreinoForca.get(6).toString());
        labelF4.setText(exerciciosTreinoForca.get(7).toString());
    }

    private void alocadorCafeDaManhaEAlmoco(List<Comida> listaDeComidasC, List<Comida> listaDeComidasA){
        lblCafe1.setText(listaDeComidasC.get(0).toString());
        lblCafe2.setText(listaDeComidasC.get(1).toString());
        lblAlmoco1.setText(listaDeComidasA.get(0).toString());
        lblAlmoco2.setText(listaDeComidasA.get(1).toString());
    }
    private void alocadorLancheEJanta(List<Comida> listaDeComidasL, List<Comida> listaDeComidasJ){
        lblLanche1.setText(listaDeComidasL.get(0).toString());
        lblLanche2.setText(listaDeComidasL.get(1).toString());
        lblLanche3.setText(listaDeComidasL.get(2).toString());
        lblJantar1.setText(listaDeComidasJ.get(0).toString());
        lblJantar2.setText(listaDeComidasJ.get(1).toString());
        if(listaDeComidasJ.size() == 3){
            lblJantar3.setText(listaDeComidasJ.get(2).toString());
        }
    }


    @FXML
    private void verificarTreinoConcluido() {
        dataAtualizada = LocalDate.now();
        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        PlanoDeTreino planoDeTreino = usuario.getPlanoDeTreinoAtual();
        Treino treinoDoDia = planoDeTreino.getTreinoList().get(checarData());

        if (chkMobilidade.isSelected() && chkCardio.isSelected() && chkForca.isSelected()) {
            if(dataAtualizada.isAfter(dataAdicionada)){
                planoDeTreino.adicionarTreinoRealizado(treinoDoDia);
                System.out.println("Treino completo adicionado ao plano!");
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
        GetLabels(labelM1, labelM2, labelM3, labelC1, labelF1, labelF2, labelF3, labelF4, labelF5, labelF6);
        GetLabels(lblCafe1, lblCafe2, lblAlmoco1, lblAlmoco2, lblLanche1, lblLanche2, lblLanche3, lblJantar1, lblJantar2, lblJantar3);

    }

    public void GetLabels(Label labelM1, Label labelM2, Label labelM3, Label labelC1, Label labelF1, Label labelF2, Label labelF3, Label labelF4, Label labelF5, Label labelF6) {
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
        MetasEstatisticasGUIController.VoltarParaTelaDaDieta(btnDietaSemanal);
    }

    @FXML
    public void btnTreinoSemanalActionPerformed() throws IOException {
        TelaDeHistoricoTreinosMetricasGUIController.VoltarParaTreinoDaSemana(btnTreinoSemanal);
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
