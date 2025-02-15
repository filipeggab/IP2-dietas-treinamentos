package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDaDieta;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDoPlano;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumSexo;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreinoPorData;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Metrica;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TelaDeSelecaoDeFocoGUIController {

    ObservableList<String> focoDaDieta =  FXCollections.observableArrayList("PERDA_DE_PESO", "GANHO_DE_MASSA", "MANUTENÇÃO", "LOWCARB", "VEGETEARIANO");
    ObservableList<String> focoDoTreino = FXCollections.observableArrayList("FORÇA_MUSCULAR", "HIPERTROFIA", "RESISTÊNCIA", "CARDIO", "FLEXIBILIDADE");
    ObservableList<EnumSexo> escolhasSexo =  FXCollections.observableArrayList(EnumSexo.values());
    @FXML
    private Button btnConfirmarSelecao;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtPeso;

    @FXML
    private ChoiceBox <EnumSexo> cbSexo;

    @FXML
    private DatePicker dpDataDeNascimento;

    @FXML
    private ChoiceBox cbFocoDaDieta;

    @FXML
    private ChoiceBox cbFocoDoTreino;

    @FXML
    private void initialize(){
        cbFocoDaDieta.setValue("PERDA_DE_PESO");
        cbFocoDoTreino.setValue("FORÇA_MUSCULAR");

        cbFocoDaDieta.setItems(focoDaDieta);
        cbFocoDoTreino.setItems(focoDoTreino);

        cbSexo.setValue(EnumSexo.MASCULINO);
        cbSexo.setItems(escolhasSexo);
    }
    @FXML
    void btnConfirmarSelecaoActionPerformed() throws IOException {
        ContinuarCadastroDoUsuario();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuário.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnConfirmarSelecao.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ContinuarCadastroDoUsuario(){
        Integer altura =  Integer.valueOf(txtAltura.getText());
        Double peso =  Double.valueOf(txtPeso.getText());
        EnumSexo sexo =  EnumSexo.valueOf(cbSexo.getSelectionModel().getSelectedItem().toString());
        LocalDate dataDeNascimento = dpDataDeNascimento.getValue();

        Metrica metricas = new Metrica(altura, peso, LocalDate.now());

        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        usuario.setSexo(sexo);
        usuario.setDataNasc(dataDeNascimento);
        usuario.adicionarMetrica(metricas);

        String nomeDaDieta = "Dieta de " + cbFocoDaDieta.getSelectionModel().getSelectedItem().toString();
        LocalDate inicioDaDieta = LocalDate.now();
        LocalDate fimDaDieta = LocalDate.now().plusWeeks(4);
        EnumObjetivoDaDieta objetivoDaDieta = EnumObjetivoDaDieta.valueOf(cbFocoDaDieta.getSelectionModel().getSelectedItem().toString());

        Dieta dieta = new Dieta(nomeDaDieta, inicioDaDieta, fimDaDieta, objetivoDaDieta);

        usuario.adicionarDieta(dieta);

        String nomeDoPlano = "Plano para " +  cbFocoDoTreino.getSelectionModel().getSelectedItem().toString();
        EnumObjetivoDoPlano objetivoDoPlano = EnumObjetivoDoPlano.valueOf(cbFocoDoTreino.getSelectionModel().getSelectedItem().toString());
        LocalDate inicioDoPlano = inicioDaDieta;
        LocalDate fimDoPlano = fimDaDieta;

        PlanoDeTreinoPorData planoDeTreino = new PlanoDeTreinoPorData(nomeDoPlano, objetivoDoPlano,inicioDoPlano,fimDoPlano);

        usuario.adicionarPlanoDeTreino(planoDeTreino);

    }



}
