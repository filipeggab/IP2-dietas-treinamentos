package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.Main;
import br.ufrpe.treinos_dietas.dados.RepositorioDietas;
import br.ufrpe.treinos_dietas.dados.RepositorioExPratico;
import br.ufrpe.treinos_dietas.dados.RepositorioPlanoDeTreino;
import br.ufrpe.treinos_dietas.exceptions.DietaNaoCadastradaException;
import br.ufrpe.treinos_dietas.exceptions.ExercicioNaoCadastradoException;
import br.ufrpe.treinos_dietas.exceptions.PlanoNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.CadastroDietas;
import br.ufrpe.treinos_dietas.negocio.CadastroPlanoDeTreino;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDaDieta;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumObjetivoDoPlano;
import br.ufrpe.treinos_dietas.negocio.beans.enums.EnumSexo;
import br.ufrpe.treinos_dietas.negocio.beans.treinos.*;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Metrica;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TelaDeSelecaoDeFocoGUIController {
    RepositorioExPratico repositorioExPratico = new  RepositorioExPratico();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();
    RepositorioDietas repositorioDietas  = new  RepositorioDietas();


    ObservableList<String> focoDaDieta =  FXCollections.observableArrayList("PERDA_DE_PESO", "GANHO_DE_MASSA", "MANUTENÇÃO", "LOWCARB", "VEGETEARIANO");
    ObservableList<String> focoDoTreino = FXCollections.observableArrayList("FORÇA_MUSCULAR", "HIPERTROFIA", "RESISTÊNCIA", "FLEXIBILIDADE");
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
    void btnConfirmarSelecaoActionPerformed() throws IOException, ExercicioNaoCadastradoException, PlanoNaoCadastradoException, DietaNaoCadastradaException {
        ContinuarCadastroDoUsuario();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/TelaPrincipalDoUsuário.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnConfirmarSelecao.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ContinuarCadastroDoUsuario() throws ExercicioNaoCadastradoException, PlanoNaoCadastradoException, DietaNaoCadastradaException {
        alocarTreino();
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

        CadastroDietas cadastroDietas = new CadastroDietas(repositorioDietas);
        cadastroDietas.cadastrarDieta(nomeDaDieta, inicioDaDieta,fimDaDieta,objetivoDaDieta);

        Dieta dieta = repositorioDietas.buscarDieta(nomeDaDieta);
        usuario.adicionarDieta(dieta);

        String nomeDoPlano = "Plano para " +  cbFocoDoTreino.getSelectionModel().getSelectedItem().toString();
        EnumObjetivoDoPlano objetivoDoPlano = EnumObjetivoDoPlano.valueOf(cbFocoDoTreino.getSelectionModel().getSelectedItem().toString());
        LocalDate inicioDoPlano = inicioDaDieta;
        LocalDate fimDoPlano = fimDaDieta;

        CadastroPlanoDeTreino cadastroPlanoDeTreino = new CadastroPlanoDeTreino(repositorioPlanoDeTreino);
        cadastroPlanoDeTreino.cadastrarPlanoDeTreino(nomeDoPlano,objetivoDoPlano,inicioDoPlano,fimDoPlano);

        PlanoDeTreino planoDeTreino = repositorioPlanoDeTreino.retornarPlanoDeTreino(nomeDoPlano);
        usuario.adicionarPlanoDeTreino(planoDeTreino);

    }
    //Criar descricoes direito pra cada um!
    public void CriarExercicios(){


        //Exercicios de peito
        Exercicio supinoReto = new Exercicio("Supino", "deite no banco, empurre a barra", 10);
        ExercicioPratico supinoRetoSerie = new ExPraticoSerieReps(supinoReto, 3, 60, 10);
        repositorioExPratico.criarExercicio(supinoRetoSerie);

        Exercicio supinoInclinado = new Exercicio("Supino Inclinado", "deite no banco, empurre a barra", 10);
        ExercicioPratico supinoInclinadoSerie = new ExPraticoSerieReps(supinoInclinado, 3, 60, 10);
        repositorioExPratico.criarExercicio(supinoInclinadoSerie);

        Exercicio fly = new Exercicio("Fly", "deite no banco, empurre a barra", 10);
        ExercicioPratico flySerie = new ExPraticoSerieReps(fly, 3, 60, 10);
        repositorioExPratico.criarExercicio(flySerie);


        //Exercicios de Cardio
        Exercicio bicicleta = new Exercicio("Bicicleta", "Faça 20 minutos de bicicleta em uma intensidade media", 160);
        ExercicioPratico BicicletaCardio = new ExPraticoCardio(bicicleta, 1200, "Média");
        repositorioExPratico.criarExercicio(BicicletaCardio);

        Exercicio esteira = new Exercicio("Esteira", "Corra em velocidade media por 20 minutos", 180);
        ExercicioPratico esteiraCardio = new ExPraticoCardio(esteira, 1200, "Média");
        repositorioExPratico.criarExercicio(esteiraCardio);

        Exercicio escada = new Exercicio("Escada", "Faça escada por 20 minutos com intensidade média", 10);
        ExercicioPratico escadaCardio = new ExPraticoCardio(escada, 1200, "Média");
        repositorioExPratico.criarExercicio(escadaCardio);


        //Exercicios de perna
        Exercicio agachamento = new Exercicio("agachamento", "agache", 10);
        ExercicioPratico agachamentoSerie = new ExPraticoSerieReps(agachamento, 3, 90, 10);
        repositorioExPratico.criarExercicio(agachamentoSerie);

        Exercicio legPress = new Exercicio("Leg Press", "empurra", 10);
        ExercicioPratico legPressSerie = new ExPraticoSerieReps(legPress, 3, 90, 8);
        repositorioExPratico.criarExercicio(legPressSerie);

        Exercicio bulgaro = new Exercicio("Búlgaro", "Com uma das pernas, simule um agachamento", 12);
        ExercicioPratico bulgaroSerie = new ExPraticoSerieReps(bulgaro, 3, 90, 10);
        repositorioExPratico.criarExercicio(bulgaroSerie);

        Exercicio mesaFlexora = new  Exercicio("Mesa Flexora", "Deite na mesa e flexione as pernas", 10);
        ExercicioPratico mesaFlexoraSerie = new ExPraticoSerieReps(mesaFlexora, 4, 90, 10);
        repositorioExPratico.criarExercicio(mesaFlexoraSerie);

        Exercicio cadeiraExtensora = new Exercicio("Cadeira Extensora", "Em uma cadeira extensora, faça o movimento de extensão dos joelhos", 10);
        ExercicioPratico cadeiraFlexoraSerie = new ExPraticoSerieReps(cadeiraExtensora, 4, 75, 12);
        repositorioExPratico.criarExercicio(cadeiraFlexoraSerie);

        Exercicio levantamentoTerra = new Exercicio("Levantamento Terra", "Segure a barra e faça um movimento de suspenção", 10);
        ExercicioPratico levantamentoTerraSerie = new ExPraticoSerieReps(levantamentoTerra, 3, 90, 8);
        repositorioExPratico.criarExercicio(levantamentoTerraSerie);


        //Exercicios de Biceps
        Exercicio roscaDireta = new Exercicio("Rosca direta", "Use a barra", 10);
        ExercicioPratico roscaDiretaSerie = new ExPraticoSerieReps(roscaDireta, 3, 60, 10);
        repositorioExPratico.criarExercicio(roscaDiretaSerie);


        Exercicio roscaAlternada = new Exercicio("Rosca Alternada", "Eleve os halteres com os biceps", 10);
        ExercicioPratico roscaAlternadaSerie = new ExPraticoSerieReps(roscaAlternada, 3, 60, 10);
        repositorioExPratico.criarExercicio(roscaAlternadaSerie);


        //Exercicios de triceps
        Exercicio tricepsCorda = new Exercicio("TricepsCorda", "Use uma corda", 10);
        ExercicioPratico tricepsCordaSerie = new ExPraticoSerieReps(tricepsCorda, 3, 60, 10);
        repositorioExPratico.criarExercicio(tricepsCordaSerie);

        Exercicio tricepsFrances = new Exercicio("Triceps Francês", "Faça utilizando uma corda acima de sua cabeça", 10);
        ExercicioPratico tricepsFrancesSerie = new ExPraticoSerieReps(tricepsFrances, 3, 60, 10);
        repositorioExPratico.criarExercicio(tricepsFrancesSerie);


        //Exercicios de ombro
        Exercicio elevacaoLateral = new Exercicio("Elevação lateral", "eleve lateralmente o halter", 10);
        ExercicioPratico elevacaoLateralSerie = new ExPraticoSerieReps(elevacaoLateral, 3, 60, 10);
        repositorioExPratico.criarExercicio(elevacaoLateralSerie);

        Exercicio elevacaoFrontal = new Exercicio("Elevação Frontal", "Eleve frontalmente  o halter", 10);
        ExercicioPratico elevacaoFrontalSerie = new ExPraticoSerieReps(elevacaoFrontal, 3, 60, 10);
        repositorioExPratico.criarExercicio(elevacaoFrontalSerie);


        //Exercicios de core
        Exercicio prancha = new Exercicio ("prancha", "fique um determinado tempo em posição de prancha", 100);
        ExercicioPratico pranchaTempo = new ExPraticoSerieTempo(prancha, 4, 60, 30);
        repositorioExPratico.criarExercicio(pranchaTempo);

        Exercicio abdominal = new Exercicio ("abdominal", "abdominal em uma intensidade media", 100);
        ExercicioPratico abdominalSerie= new ExPraticoSerieReps(abdominal, 5, 60, 20);
        repositorioExPratico.criarExercicio(abdominalSerie);


        //Exercicios de costas
        Exercicio pulldown = new Exercicio("pulldown", "puxe a barra para baixo, trazendo até a parte superior do peito", 10);
        ExercicioPratico pulldownSerie = new ExPraticoSerieReps(pulldown, 3, 60, 10);
        repositorioExPratico.criarExercicio(pulldownSerie);

        Exercicio puxadaAlta = new Exercicio("Puxada alta", "Puxe a barra para cima, elevando até a altura do peito", 10);
        ExercicioPratico puxadaAltaSerie = new ExPraticoSerieReps(puxadaAlta, 3, 60, 10);
        repositorioExPratico.criarExercicio(puxadaAltaSerie);

        Exercicio remadaCavalinho = new Exercicio("Remada cavalinho", "Puxe a barra em direção ao abdômen, contraindo as costas", 10);
        ExercicioPratico remadaCavalinhoSerie = new ExPraticoSerieReps(remadaCavalinho, 3, 60, 10);
        repositorioExPratico.criarExercicio(remadaCavalinhoSerie);

        Exercicio remadaBaixa = new Exercicio ("Remada Baixa", "puxe a corda na sua direção", 10);
        ExercicioPratico remadaSerie = new ExPraticoSerieReps(remadaBaixa, 3, 60, 10);
        repositorioExPratico.criarExercicio(remadaSerie);


        //mobilidades
        Exercicio mobilidadeDeQuadril = new Exercicio("Mobilidade de quadril", "Empurre os quadris para frente, mantendo a parte superior do corpo ereta", 1);
        ExercicioPratico mobilidadeDeQuadrilSerie = new ExPraticoSerieReps(mobilidadeDeQuadril, 1, 0, 30);
        repositorioExPratico.criarExercicio(mobilidadeDeQuadrilSerie);

        Exercicio rotacaoToracica = new Exercicio("Rotação torácica", "Gire o tronco para cima, levando o cotovelo da mão de cima em direção ao teto, mantendo os quadris fixos", 1);
        ExercicioPratico rotacaoToracicaSerie = new ExPraticoSerieReps(rotacaoToracica, 1, 0 , 12);
        repositorioExPratico.criarExercicio(rotacaoToracicaSerie);

        Exercicio circuloOmbros = new Exercicio("circulo com os ombros", "Levante os ombros para cima, mova-os para trás em um círculo e depois para baixo", 1);
        ExercicioPratico circuloOmbrosSerie = new ExPraticoSerieReps(circuloOmbros, 1, 0, 15);
        repositorioExPratico.criarExercicio(circuloOmbrosSerie);

        Exercicio circuloQuadris = new Exercicio("Circulo com os quadris", "Coloque as mãos nos quadris e comece a fazer circulos, primeiro no sentido horario, depois no anti-horario", 1);
        ExercicioPratico circuloQuadrisSerie = new  ExPraticoSerieReps(circuloQuadris, 1, 0, 15);
        repositorioExPratico.criarExercicio(circuloQuadrisSerie);

        Exercicio flexaoDeColuna = new Exercicio("Flexão de coluna", "Em posição de quatro apoios, alterne entre arquear as costas e curvá-las para baixo.", 1);
        ExercicioPratico flexaoDeColunaSerie = new ExPraticoSerieReps(flexaoDeColuna, 1, 0, 15);
        repositorioExPratico.criarExercicio(flexaoDeColunaSerie);

        Exercicio rotacaoDeCotovelos = new Exercicio("Rotação de Cotovelos", "Com os braços flexionados a 90 graus, gire os antebraços para dentro e para fora.", 1);
        ExercicioPratico rotacaoDeCotovelosSerie = new ExPraticoSerieReps(rotacaoDeCotovelos,  1, 0, 15);
        repositorioExPratico.criarExercicio(rotacaoDeCotovelosSerie);

        Exercicio movimentacaoDePunho = new Exercicio("Movimentação de punhos", "Faça círculos com os punhos e flexione-os para cima e para baixo", 1);
        ExercicioPratico movimentacaoDePunhoSerie = new ExPraticoSerieReps(movimentacaoDePunho, 1, 0, 15);
        repositorioExPratico.criarExercicio(movimentacaoDePunhoSerie);

        Exercicio mobilizacaoDeTornozelo = new Exercicio("Mobilização de Tornozelo", "Sentado ou em pé, faça movimentos circulares com os tornozelos e flexione-os para cima e para baixo", 1);
        ExercicioPratico mobilizacaoDeTornozeloSerie = new ExPraticoSerieReps(mobilizacaoDeTornozelo,  1, 0, 15);
        repositorioExPratico.criarExercicio(mobilizacaoDeTornozeloSerie);
    }

    //adicionar repositorios nos exercicios praticos e tentar alocar!
    public void alocarTreino() throws ExercicioNaoCadastradoException {
        CriarExercicios();
        switch(cbFocoDoTreino.getSelectionModel().getSelectedItem().toString()){
            case "HIPERTROFIA":


            case "FORÇA_MUSCULAR":


            case "FLEXIBILIDADE":


            case "RESISTÊNCIA":


            default:
                break;
        }

    }

}
