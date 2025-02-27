package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.Treino;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.TreinoRealizado;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Metrica;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaDeHistoricoTreinosMetricasGUIController {
    Usuario usuario = SessaoUsuario.getInstancia().getUsuario();

    private int dias = 0;

    private int getDias(){
        return dias;
    }

    private void setDias(int dias){
        this.dias = dias;
    }

    @FXML
    private VBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12;

    @FXML
    private Button btnVoltarParaTreinoDaSemana;

    @FXML
    public void btnVoltarParaTreinoDaSemanaActionPerformed() throws IOException {
        VoltarParaTreinoDaSemana(btnVoltarParaTreinoDaSemana);
    }

    public static void VoltarParaTreinoDaSemana(Button btnVoltarParaTreinoDaSemana) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaDoTreinoDaSemana.fxml"));
        Parent root = loader.load();

        TelaDoTreinoDaSemanaGUIController controller = loader.getController();
        controller.alocadorLabelsTreinos();

        Stage stage = (Stage) btnVoltarParaTreinoDaSemana.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public List<Label> getLabelsVbox(VBox vbox) {
        List<Label> labels = new ArrayList<>();
        for (Node node : vbox.getChildren()) {
            if (node instanceof Label && !((Label)node).getText().equals("métricas")) {
                labels.add((Label) node);
            }
        }
        return labels;
    }

    public void alocadorLabels(int diasExtras) {
        VBox[] vBoxList = new VBox[]{box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12};
        List<TreinoRealizado> treinosRealizados = usuario.getPlanoDeTreinoAtual().getTreinoRealizadoList();
        List<Metrica> metricas = usuario.getMetricaList();
        setDias(1 + diasExtras);

        if (treinosRealizados == null || treinosRealizados.isEmpty()) {
            System.out.println("Nenhum treinoRealizado.");
        }

        if (metricas == null || metricas.isEmpty()) {
            System.out.println("Nenhuma métrica disponível.");
        }

        for (VBox vbox : vBoxList) {
            List<Label> labels = getLabelsVbox(vbox);

            if (labels.size() == 1) {
                labels.get(0).setText(getDias() + " Dia(s) atrás");

            } else if (labels.size() == 3) {
                if (metricas.size() - getDias() >= 0) {

                    Metrica metrica = metricas.get(metricas.size() - getDias());
                    labels.get(0).setText("Altura: " + metrica.getAltura());
                    labels.get(1).setText("Peso: " + metrica.getPeso());
                    double imc = metrica.calcularIMC();
                    String formattedIMC = String.format("%.2f", imc);  // Format to 2 decimal places
                    labels.get(2).setText("IMC: " + formattedIMC);
                } else {

                    labels.get(0).setText("Altura: N/A");
                    labels.get(1).setText("Peso: N/A");
                    labels.get(2).setText("IMC: N/A");
                }

            } else if (labels.size() == 13) {

                Treino treinoAtual;
                if (treinosRealizados.size() - getDias() < 0) {
                    for (int i = 0; i < 13; i++) {
                        labels.get(i).setText("N/A");
                    }
                    setDias(getDias() + 1);

                }else {
                    treinoAtual = treinosRealizados.get(treinosRealizados.size() - getDias()).getTreinoRealizado();


                    for (int i = 0; i < Math.min(labels.size(), treinoAtual.getExercicioList().size()); i++) {
                        labels.get(i).setText(treinoAtual.getExercicioList().get(i).getExercicio().getNome());
                    }
                    setDias(getDias() + 1);
                }
            }

        }
    }
}
