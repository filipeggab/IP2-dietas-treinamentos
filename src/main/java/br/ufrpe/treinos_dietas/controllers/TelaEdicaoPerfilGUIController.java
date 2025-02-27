package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.dados.RepositorioDietas;
import br.ufrpe.treinos_dietas.dados.RepositorioPlanoDeTreino;
import br.ufrpe.treinos_dietas.exceptions.*;
import br.ufrpe.treinos_dietas.negocio.CadastroUsuarios;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumSexo;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.PlanoDeTreino;
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

public class TelaEdicaoPerfilGUIController {
    ObservableList<String> focoDaDieta =  FXCollections.observableArrayList("PERDA_DE_PESO", "GANHO_DE_MASSA", "MANUTENÇÃO");
    ObservableList<String> focoDoTreino = FXCollections.observableArrayList("FORÇA_MUSCULAR", "HIPERTROFIA", "RESISTÊNCIA", "FLEXIBILIDADE");
    RepositorioDietas repositorioDietas = RepositorioDietas.getInstance();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();

    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDeSelecaoDeFocoGUIController.fxml"));
    CadastroUsuarios cadastroUsuarios = new CadastroUsuarios();
    @FXML
    private Label lblFeedback;
    @FXML
    private TextField txtNome, txtEmail, txtAltura, txtPeso;
    @FXML
    private DatePicker dpDataDeNascimento;
    @FXML
    private ComboBox<EnumSexo> cbSexo;
    @FXML
    private Button btnSalvar, btnCancelar;
    @FXML
    private ChoiceBox<String> cbFocoDoTreino, cbFocoDaDieta;

    private Usuario usuario;
    private Metrica ultimaMetrica;

    public TelaEdicaoPerfilGUIController() throws IOException {
    }

    @FXML
    public void initialize() {
        usuario = SessaoUsuario.getInstancia().getUsuario();
        String nomeDieta = usuario.getDietaAtual().getNome();
        String nomeTreino = usuario.getPlanoDeTreinoAtual().getNome();

        cbFocoDaDieta.setValue(nomeDieta);
        cbFocoDoTreino.setValue(nomeTreino);

        cbFocoDaDieta.setItems(focoDaDieta);
        cbFocoDoTreino.setItems(focoDoTreino);

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
    public void btnSalvarActionPerformed() throws IOException, DietaNaoCadastradaException, ComidaNaoCadastradaException, TreinoNaoCadastradoException, PlanoNaoCadastradoException, ExercicioNaoCadastradoException, EmailInvalidoException, UsuarioNaoCadastradoException {
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
            String nomeDieta = usuario.getDietaAtual().getNome();
            if(!cbFocoDaDieta.getSelectionModel().getSelectedItem().equals(nomeDieta)){
                int checker = 0;
                if(repositorioDietas.dietaExiste("Dieta de " + cbFocoDaDieta.getSelectionModel().getSelectedItem())){
                    String novaDieta = "Dieta de " + cbFocoDaDieta.getSelectionModel().getSelectedItem();
                    for(Dieta dietas: usuario.getListDietas()){;
                        if(dietas.getNome().equals(novaDieta)){
                            usuario.setDietaAtual(dietas);
                            checker = 1;
                            break;
                        }
                    }
                }
                if(checker == 0){
                    TelaDeSelecaoDeFocoGUIController controller =  new TelaDeSelecaoDeFocoGUIController();
                    controller.CadastrarDieta(cbFocoDaDieta, null);
                }
            }
            String nomeTreino = usuario.getPlanoDeTreinoAtual().getNome();
            if(!cbFocoDoTreino.getSelectionModel().getSelectedItem().equals(nomeTreino)){
                int checker = 0;
                if(repositorioPlanoDeTreino.planoDeTreinoExiste("Plano para " + cbFocoDoTreino.getSelectionModel().getSelectedItem())){
                    String novoPlano = "Plano para " + cbFocoDoTreino.getSelectionModel().getSelectedItem();
                    for(PlanoDeTreino planos : usuario.getPlanoDeTreinoList()){
                        if(planos.getNome().equals(novoPlano)){
                            usuario.setPlanoDeTreinoAtual(planos);
                            checker = 1;
                            break;
                        }
                    }
                }
                if(checker == 0){
                    TelaDeSelecaoDeFocoGUIController controller = new TelaDeSelecaoDeFocoGUIController();
                    controller.CadastrarTreino(cbFocoDoTreino, null);
                }
            }

            // Verifica se os valores de altura e peso foram alterados antes de criar uma nova métrica
            try {
                int novaAltura = Integer.parseInt(txtAltura.getText());
                double novoPeso = Double.parseDouble(txtPeso.getText());

                if (ultimaMetrica == null || ultimaMetrica.getAltura() != novaAltura || ultimaMetrica.getPeso() != novoPeso) {
                    Metrica novaMetrica = new Metrica(novaAltura, novoPeso, LocalDate.now());
                    usuario.adicionarMetrica(novaMetrica);
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Altura e Peso devem ser valores numéricos.");
            }
        }

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
