package br.ufrpe.treinos_dietas.controllers;

import br.ufrpe.treinos_dietas.dados.*;
import br.ufrpe.treinos_dietas.exceptions.*;
import br.ufrpe.treinos_dietas.negocio.*;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Dieta;
import br.ufrpe.treinos_dietas.negocio.beans.dietas.Refeicao;
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
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TelaDeSelecaoDeFocoGUIController {
    RepositorioExPratico repositorioExPratico = new RepositorioExPratico();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = RepositorioPlanoDeTreino.getInstance();
    RepositorioDietas repositorioDietas  = RepositorioDietas.getInstance();
    RepositorioTreinos repositorioTreinos = new RepositorioTreinos();
    RepositorioComidas repositorioComidas = new RepositorioComidas();




    ObservableList<String> focoDaDieta =  FXCollections.observableArrayList("PERDA_DE_PESO", "GANHO_DE_MASSA", "MANUTENÇÃO");
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
    private ChoiceBox<String> cbFocoDaDieta;

    @FXML
    private ChoiceBox<String> cbFocoDoTreino;

    @FXML
    private Label lblErroCadastro;

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
    void btnConfirmarSelecaoActionPerformed() throws IOException, ExercicioNaoCadastradoException, PlanoNaoCadastradoException, DietaNaoCadastradaException, TreinoNaoCadastradoException {
        try {
            ContinuarCadastroDoUsuario();
            TelaDoTreinoDaSemanaGUIController.VoltarParaTelaPrincipalDoUsuario(btnConfirmarSelecao);
        } catch (NumberFormatException | NullPointerException | ComidaNaoCadastradaException e) {
            lblErroCadastro.setText("Preencha todos os campos corretamente!!");
        }
    }

    public void ContinuarCadastroDoUsuario() throws ExercicioNaoCadastradoException, PlanoNaoCadastradoException, DietaNaoCadastradaException, TreinoNaoCadastradoException, ComidaNaoCadastradaException {
        try {
            if (txtAltura.getText().isEmpty() || txtPeso.getText().isEmpty() || cbSexo.getSelectionModel().isEmpty() || dpDataDeNascimento.getValue() == null) {
                lblErroCadastro.setText("Preencha todos os campos corretamente!");
                throw new IllegalArgumentException();
            }

            int altura = Integer.parseInt(txtAltura.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            EnumSexo sexo = EnumSexo.valueOf(cbSexo.getSelectionModel().getSelectedItem().toString());
            LocalDate dataDeNascimento = dpDataDeNascimento.getValue();

            Metrica metricas = new Metrica(altura, peso, LocalDate.now());

            Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
            usuario.setSexo(sexo);
            usuario.setDataNasc(dataDeNascimento);
            usuario.adicionarMetrica(metricas);

            CadastrarDieta(cbFocoDaDieta, null);
            CadastrarTreino(cbFocoDoTreino, null);
            CadastroUsuarios cadastroUsuarios = new CadastroUsuarios();
            cadastroUsuarios.cadastrarUsuario(usuario);

        } catch (NumberFormatException e) {
            lblErroCadastro.setText("Altura e peso devem ser números válidos.");
            throw e;
        } catch (IllegalArgumentException e) {
            lblErroCadastro.setText("Selecione uma data de nascimento válida.");
            throw e;
        } catch (ExercicioNaoCadastradoException e) {
            lblErroCadastro.setText("Exercício não cadastrado.");
            throw e;
        }
    }




    public void CadastrarDieta(ChoiceBox<String> escolha, String nome) throws DietaNaoCadastradaException, ComidaNaoCadastradaException {
        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        List<Dieta> listaAtualDeDietas = usuario.getListDietas();
        for(Dieta dieta : listaAtualDeDietas){
            if(dieta.getNome().equals(nome)){
                usuario.setDietaAtual(dieta);
                return;
            }
        }

        String nomeDaDieta = "Dieta de " + escolha.getSelectionModel().getSelectedItem();
        LocalDate inicioDaDieta = LocalDate.now();
        LocalDate fimDaDieta = LocalDate.now().plusWeeks(4);
        EnumObjetivoDaDieta objetivoDaDieta = EnumObjetivoDaDieta.valueOf(escolha.getSelectionModel().getSelectedItem());

        CadastroDietas cadastroDietas = new CadastroDietas(repositorioDietas);
        cadastroDietas.cadastrarDieta(nomeDaDieta, inicioDaDieta,fimDaDieta,objetivoDaDieta);
        Dieta dieta = repositorioDietas.buscarDieta(nomeDaDieta);
        usuario.adicionarDieta(dieta);
        usuario.setDietaAtual(dieta);

        CriarRefeicoes(escolha);

    }

    public void CadastrarTreino(ChoiceBox<String> escolha, String nome) throws PlanoNaoCadastradoException, TreinoNaoCadastradoException, ExercicioNaoCadastradoException {
        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        List<PlanoDeTreino> listaAtualDePlanos = usuario.getPlanoDeTreinoList();
        for(PlanoDeTreino plano : listaAtualDePlanos){
            if(plano.getNome().equals(nome)){
                usuario.setPlanoDeTreinoAtual(plano);
                return;
            }
        }

        String nomeDoPlano = "Plano para " +  escolha.getSelectionModel().getSelectedItem();
        EnumObjetivoDoPlano objetivoDoPlano = EnumObjetivoDoPlano.valueOf(escolha.getSelectionModel().getSelectedItem());
        LocalDate inicioDoPlano = LocalDate.now();
        LocalDate fimDoPlano = LocalDate.now();

        CadastroPlanoDeTreino cadastroPlanoDeTreino = new CadastroPlanoDeTreino(repositorioPlanoDeTreino);
        cadastroPlanoDeTreino.cadastrarPlanoDeTreino(nomeDoPlano,objetivoDoPlano,inicioDoPlano,fimDoPlano);

        PlanoDeTreino planoDeTreino = repositorioPlanoDeTreino.retornarPlanoDeTreino(nomeDoPlano);
        usuario.adicionarPlanoDeTreino(planoDeTreino);
        usuario.setPlanoDeTreinoAtual(planoDeTreino);

        AlocarTreino(escolha);
    }


    public void CriarExercicios(){


        //Exercicios de peito
        Exercicio supinoReto = new Exercicio("Supino Reto", "deite no banco, empurre a barra", 10);
        ExercicioPratico supinoRetoSerie = new ExPraticoSerieReps(supinoReto, 4, 75, 8);
        repositorioExPratico.criarExercicio(supinoRetoSerie);

        Exercicio supinoInclinado = new Exercicio("Supino Inclinado", "deite no banco, empurre a barra", 10);
        ExercicioPratico supinoInclinadoSerie = new ExPraticoSerieReps(supinoInclinado, 3, 75, 10);
        repositorioExPratico.criarExercicio(supinoInclinadoSerie);

        Exercicio fly = new Exercicio("Fly", "deite no banco, empurre a barra", 10);
        ExercicioPratico flySerie = new ExPraticoSerieReps(fly, 4, 60, 12);
        repositorioExPratico.criarExercicio(flySerie);

        Exercicio crossover = new Exercicio("Crossover", "Nas polias, faça um movimento de abraço", 10);
        ExercicioPratico crossoverSerie = new ExPraticoSerieReps(crossover, 3, 60, 10);
        repositorioExPratico.criarExercicio(crossoverSerie);


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
        Exercicio agachamento = new Exercicio("agachamento", "Com a barra nas costas, faça o movimento de agacho", 10);
        ExercicioPratico agachamentoSerie = new ExPraticoSerieReps(agachamento, 3, 90, 10);
        repositorioExPratico.criarExercicio(agachamentoSerie);

        Exercicio legPress = new Exercicio("Leg Press", "Deite na maquina e empurre com suas pernas", 10);
        ExercicioPratico legPressSerie = new ExPraticoSerieReps(legPress, 3, 90, 8);
        repositorioExPratico.criarExercicio(legPressSerie);

        Exercicio bulgaro = new Exercicio("Búlgaro", "Com uma das pernas, simule um agachamento", 12);
        ExercicioPratico bulgaroSerie = new ExPraticoSerieReps(bulgaro, 2, 90, 10);
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

        Exercicio stiff = new Exercicio("Stiff", "Segure a barra e desça suas costas com as penas retas", 10);
        ExercicioPratico stiffSerie = new ExPraticoSerieReps(stiff, 3, 90, 8);
        repositorioExPratico.criarExercicio(stiffSerie);


        //Exercicios de Biceps
        Exercicio roscaDireta = new Exercicio("Rosca direta", "Use a barra", 10);
        ExercicioPratico roscaDiretaSerie = new ExPraticoSerieReps(roscaDireta, 5, 60, 10);
        repositorioExPratico.criarExercicio(roscaDiretaSerie);

        Exercicio roscaAlternada = new Exercicio("Rosca alternada", "Eleve os halteres com os biceps", 10);
        ExercicioPratico roscaAlternadaSerie = new ExPraticoSerieReps(roscaAlternada, 3, 60, 10);
        repositorioExPratico.criarExercicio(roscaAlternadaSerie);


        //Exercicios de triceps
        Exercicio tricepsCorda = new Exercicio("Triceps Corda", "Use uma corda", 10);
        ExercicioPratico tricepsCordaSerie = new ExPraticoSerieReps(tricepsCorda, 5, 60, 10);
        repositorioExPratico.criarExercicio(tricepsCordaSerie);

        Exercicio tricepsFrances = new Exercicio("Triceps Francês", "Faça utilizando uma corda acima de sua cabeça", 10);
        ExercicioPratico tricepsFrancesSerie = new ExPraticoSerieReps(tricepsFrances, 3, 60, 10);
        repositorioExPratico.criarExercicio(tricepsFrancesSerie);


        //Exercicios de ombro
        Exercicio elevacaoLateral = new Exercicio("Elevação lateral", "eleve lateralmente o halter", 10);
        ExercicioPratico elevacaoLateralSerie = new ExPraticoSerieReps(elevacaoLateral, 4, 60, 12);
        repositorioExPratico.criarExercicio(elevacaoLateralSerie);

        Exercicio elevacaoFrontal = new Exercicio("Elevação Frontal", "Eleve frontalmente o halter", 10);
        ExercicioPratico elevacaoFrontalSerie = new ExPraticoSerieReps(elevacaoFrontal, 4, 60, 12);
        repositorioExPratico.criarExercicio(elevacaoFrontalSerie);

        Exercicio desenvolvimento = new Exercicio("Desenvolvimento", "Segure os halteres e levante com os braços flexionados", 10);
        ExercicioPratico desenvolvimentoSerie = new ExPraticoSerieReps(desenvolvimento, 3, 60, 8);
        repositorioExPratico.criarExercicio(desenvolvimentoSerie);


        //Exercicios de core
        Exercicio prancha = new Exercicio ("Prancha", "fique um determinado tempo em posição de prancha", 100);
        ExercicioPratico pranchaTempo = new ExPraticoSerieTempo(prancha, 4, 60, 30);
        repositorioExPratico.criarExercicio(pranchaTempo);

        Exercicio abdominal = new Exercicio ("Abdominal", "abdominal em uma intensidade media", 100);
        ExercicioPratico abdominalSerie= new ExPraticoSerieReps(abdominal, 5, 60, 20);
        repositorioExPratico.criarExercicio(abdominalSerie);


        //Exercicios de costas
        Exercicio pulldown = new Exercicio("Pulldown", "puxe a barra para baixo, trazendo até a parte superior do peito", 10);
        ExercicioPratico pulldownSerie = new ExPraticoSerieReps(pulldown, 3, 60, 8);
        repositorioExPratico.criarExercicio(pulldownSerie);

        Exercicio puxadaAlta = new Exercicio("Puxada alta", "Puxe a barra para cima, elevando até a altura do peito", 10);
        ExercicioPratico puxadaAltaSerie = new ExPraticoSerieReps(puxadaAlta, 5, 60, 12);
        repositorioExPratico.criarExercicio(puxadaAltaSerie);

        Exercicio remadaCavalinho = new Exercicio("Remada cavalinho", "Puxe a barra em direção ao abdômen, contraindo as costas", 10);
        ExercicioPratico remadaCavalinhoSerie = new ExPraticoSerieReps(remadaCavalinho, 3, 60, 10);
        repositorioExPratico.criarExercicio(remadaCavalinhoSerie);

        Exercicio remadaBaixa = new Exercicio ("Remada baixa", "puxe a corda na sua direção", 10);
        ExercicioPratico remadaSerie = new ExPraticoSerieReps(remadaBaixa, 3, 60, 10);
        repositorioExPratico.criarExercicio(remadaSerie);

        Exercicio serrote = new Exercicio("Serrote", "Segure o halter e puxe em direção as suas costas", 10);
        ExercicioPratico serroteSerie = new ExPraticoSerieReps(serrote, 4, 60, 10);
        repositorioExPratico.criarExercicio(serroteSerie);


        //mobilidades
        Exercicio mobilidadeDeQuadril = new Exercicio("Mobilidade de quadril", "Empurre os quadris para frente, mantendo a parte superior do corpo ereta", 1);
        ExercicioPratico mobilidadeDeQuadrilSerie = new ExPraticoSerieReps(mobilidadeDeQuadril, 1, 0, 30);
        repositorioExPratico.criarExercicio(mobilidadeDeQuadrilSerie);

        Exercicio rotacaoToracica = new Exercicio("Rotação torácica", "Gire o tronco para cima, levando o cotovelo da mão de cima em direção ao teto, mantendo os quadris fixos", 1);
        ExercicioPratico rotacaoToracicaSerie = new ExPraticoSerieReps(rotacaoToracica, 1, 0 , 12);
        repositorioExPratico.criarExercicio(rotacaoToracicaSerie);

        Exercicio circuloOmbros = new Exercicio("Circulo com os ombros", "Levante os ombros para cima, mova-os para trás em um círculo e depois para baixo", 1);
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
    public void AlocarTreino(ChoiceBox<String> escolha) throws ExercicioNaoCadastradoException, TreinoNaoCadastradoException, PlanoNaoCadastradoException {
        CriarExercicios();
        CadastroTreinos cadastroTreinos = new CadastroTreinos(repositorioTreinos);
        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        String selecter = escolha.getSelectionModel().getSelectedItem();
        switch(selecter){
            case "HIPERTROFIA": //media de exercicios
                List<ExercicioPratico> treinoAHipertrofia = new ArrayList<>();
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Bicicleta"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Supino Reto"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Supino Inclinado"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Fly"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Triceps Corda"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Triceps Francês"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Elevação lateral"));
                treinoAHipertrofia.add(repositorioExPratico.retornarExPratico("Elevação Frontal"));
                List<ExercicioPratico> treinoBHipertrofia = new ArrayList<>();
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Esteira"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Pulldown"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Puxada alta"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Remada cavalinho"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Remada baixa"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Rosca direta"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Rosca alternada"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Abdominal"));
                treinoBHipertrofia.add(repositorioExPratico.retornarExPratico("Prancha"));
                List<ExercicioPratico> treinoCHipertrofia = new ArrayList<>();
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Mobilidade de quadril"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Rotação torácica"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Circulo com os quadris"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Escada"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("agachamento"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Leg Press"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Búlgaro"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Mesa Flexora"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Cadeira Extensora"));
                treinoCHipertrofia.add(repositorioExPratico.retornarExPratico("Levantamento terra"));

                cadastroTreinos.cadastrarTreino("Treino A Hipertrofia", treinoAHipertrofia);
                cadastroTreinos.cadastrarTreino("Treino B Hipertrofia", treinoBHipertrofia);
                cadastroTreinos.cadastrarTreino("Treino C Hipertrofia", treinoCHipertrofia);
                Treino treinoAHip = repositorioTreinos.buscarTreino("Treino A Hipertrofia");
                Treino treinoBHip = repositorioTreinos.buscarTreino("Treino B Hipertrofia");
                Treino treinoCHip = repositorioTreinos.buscarTreino("Treino C Hipertrofia");
                List<Treino> listaDeTreinosDeHipertrofia = new ArrayList<>();
                listaDeTreinosDeHipertrofia.add(treinoAHip);
                listaDeTreinosDeHipertrofia.add(treinoBHip);
                listaDeTreinosDeHipertrofia.add(treinoCHip);
                usuario.AdicionarListaAoPlanoDeTreino(repositorioPlanoDeTreino.retornarNomeDoPlanoDeTreino(), listaDeTreinosDeHipertrofia);
                break;


            case "FORÇA_MUSCULAR": //-exercicios
                List<ExercicioPratico> treinoAForca =  new ArrayList<>();
                treinoAForca.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoAForca.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoAForca.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoAForca.add(repositorioExPratico.retornarExPratico("Bicicleta"));
                treinoAForca.add(repositorioExPratico.retornarExPratico("Supino Reto"));
                treinoAForca.add(repositorioExPratico.retornarExPratico("Supino Inclinado"));
                treinoAForca.add(repositorioExPratico.retornarExPratico("Triceps Corda"));
                treinoAForca.add(repositorioExPratico.retornarExPratico("Elevação lateral"));
                List<ExercicioPratico> treinoBForca =  new ArrayList<>();
                treinoBForca.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Esteira"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Puxada alta"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Remada cavalinho"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Remada baixa"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Rosca direta"));
                treinoBForca.add(repositorioExPratico.retornarExPratico("Abdominal"));
                List<ExercicioPratico> treinoCForca =  new ArrayList<>();
                treinoCForca.add(repositorioExPratico.retornarExPratico("Mobilidade de quadril"));
                treinoCForca.add(repositorioExPratico.retornarExPratico("Rotação torácica"));
                treinoCForca.add(repositorioExPratico.retornarExPratico("Circulo com os quadris"));
                treinoCForca.add(repositorioExPratico.retornarExPratico("Escada"));
                treinoCForca.add(repositorioExPratico.retornarExPratico("agachamento"));
                treinoCForca.add(repositorioExPratico.retornarExPratico("Leg Press"));
                treinoCForca.add(repositorioExPratico.retornarExPratico("Levantamento terra"));

                cadastroTreinos.cadastrarTreino("Treino A Força", treinoAForca);
                cadastroTreinos.cadastrarTreino("Treino B Força", treinoBForca);
                cadastroTreinos.cadastrarTreino("Treino C Força", treinoCForca);
                Treino treinoAFM = repositorioTreinos.buscarTreino("Treino A Força");
                Treino treinoBFM = repositorioTreinos.buscarTreino("Treino B Força");
                Treino treinoCFM = repositorioTreinos.buscarTreino("Treino C Força");
                List<Treino> listaDeTreinosDeForca =  new ArrayList<>();
                listaDeTreinosDeForca.add(treinoAFM);
                listaDeTreinosDeForca.add(treinoBFM);
                listaDeTreinosDeForca.add(treinoCFM);
                usuario.AdicionarListaAoPlanoDeTreino(repositorioPlanoDeTreino.retornarNomeDoPlanoDeTreino(), listaDeTreinosDeForca);
                break;

            case "FLEXIBILIDADE": //++Mobilidade --Exercicios
                List<ExercicioPratico> treinoAFlexibilidade = new ArrayList<>();
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Mobilidade de quadril"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Circulo com os quadris"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Flexão de coluna"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Bicicleta"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Supino reto"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Triceps corda"));
                treinoAFlexibilidade.add(repositorioExPratico.retornarExPratico("Elevação lateral"));
                List<ExercicioPratico> treinoBFlexibilidade = new ArrayList<>();
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Flexão de coluna"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Mobilização de Tornozelo"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Circulo com os quadris"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Rotação torácica"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Escada"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Puxada alta"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Remada baixa"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("Rosca direta"));
                treinoBFlexibilidade.add(repositorioExPratico.retornarExPratico("prancha"));
                List<ExercicioPratico> treinoCFlexibilidade = new ArrayList<>();
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Mobilidade de quadril"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Rotação torácica"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Circulo com os ombros"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Circulo com os quadris"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Flexão de coluna"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Mobilização de Tornozelo"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Bicicleta"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Agachamento"));
                treinoCFlexibilidade.add(repositorioExPratico.retornarExPratico("Mesa Flexora"));

                cadastroTreinos.cadastrarTreino("Treino A FLexibilidade", treinoAFlexibilidade);
                cadastroTreinos.cadastrarTreino("Treino B FLexibilidade", treinoBFlexibilidade);
                cadastroTreinos.cadastrarTreino("Treino C FLexibilidade", treinoCFlexibilidade);
                Treino treinoAFlex = repositorioTreinos.buscarTreino("Treino A FLexibilidade");
                Treino treinoBFlex = repositorioTreinos.buscarTreino("Treino B FLexibilidade");
                Treino treinoCFlex = repositorioTreinos.buscarTreino("Treino C Flexibilidade");

                List<Treino> listaDeTreinosDeFlexibilidade = new ArrayList<>();
                listaDeTreinosDeFlexibilidade.add(treinoAFlex);
                listaDeTreinosDeFlexibilidade.add(treinoBFlex);
                listaDeTreinosDeFlexibilidade.add(treinoCFlex);
                usuario.AdicionarListaAoPlanoDeTreino(repositorioPlanoDeTreino.retornarNomeDoPlanoDeTreino(),  listaDeTreinosDeFlexibilidade);
                break;

            case "RESISTÊNCIA": //++Exercicios
                List<ExercicioPratico> treinoAResistencia = new ArrayList<>();
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Bicicleta"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Supino Reto"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Supino Inclinado"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Fly"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Crossover"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Triceps Corda"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Triceps Francês"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Elevação lateral"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Elevação Frontal"));
                treinoAResistencia.add(repositorioExPratico.retornarExPratico("Desenvolvimento"));
                List<ExercicioPratico> treinoBResistencia = new ArrayList<>();
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("circulo com os ombros"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Rotação de Cotovelos"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Movimentação de punhos"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Esteira"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Pulldown"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Serrote"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Puxada alta"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Remada cavalinho"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Remada baixa"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Rosca direta"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Rosca alternada"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Abdominal"));
                treinoBResistencia.add(repositorioExPratico.retornarExPratico("Prancha"));
                List<ExercicioPratico> treinoCResistencia = new ArrayList<>();
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Mobilidade de quadril"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Rotação torácica"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Circulo com os quadris"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Mobilização de tornozelo"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Escada"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("agachamento"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Leg Press"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Búlgaro"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Mesa Flexora"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Cadeira Extensora"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Levantamento terra"));
                treinoCResistencia.add(repositorioExPratico.retornarExPratico("Stiff"));

                cadastroTreinos.cadastrarTreino("Treino A Resistência", treinoAResistencia);
                cadastroTreinos.cadastrarTreino("Treino B Resistência", treinoBResistencia);
                cadastroTreinos.cadastrarTreino("Treino C Resistência", treinoCResistencia);
                Treino treinoARes = repositorioTreinos.buscarTreino("Treino A Resistência");
                Treino treinoBRes = repositorioTreinos.buscarTreino("Treino B Resistência");
                Treino treinoCRes = repositorioTreinos.buscarTreino("Treino C Resistência");

                List<Treino> listaDeTreinosDeResistencia = new ArrayList<>();
                listaDeTreinosDeResistencia.add(treinoARes);
                listaDeTreinosDeResistencia.add(treinoBRes);
                listaDeTreinosDeResistencia.add(treinoCRes);
                usuario.AdicionarListaAoPlanoDeTreino(repositorioPlanoDeTreino.retornarNomeDoPlanoDeTreino(),  listaDeTreinosDeResistencia);
                break;

            default:
                break;
        }

    }

    public void CriarComidas(){
        CadastroComidas cadastroComidas = new CadastroComidas(repositorioComidas);
        cadastroComidas.cadastrarComida("Brown Bread", "80g");
        cadastroComidas.cadastrarComida("Bread", "140g");
        cadastroComidas.cadastrarComida("Banana", "80g");
        cadastroComidas.cadastrarComida("Chicken Breast", "100g");
        cadastroComidas.cadastrarComida("Chicken Breast", "150g");
        cadastroComidas.cadastrarComida("Rice", "150g");
        cadastroComidas.cadastrarComida("Rice", "200g");
        cadastroComidas.cadastrarComida("Rice", "250g");
        cadastroComidas.cadastrarComida("Beans", "100g");
        cadastroComidas.cadastrarComida("Beans", "200g");
        cadastroComidas.cadastrarComida("Eggs", "100g");
        cadastroComidas.cadastrarComida("Eggs", "150g");
        cadastroComidas.cadastrarComida("Potatoes", "70g");
        cadastroComidas.cadastrarComida("Sweet potatoes" , "140g");
        cadastroComidas.cadastrarComida("Beef", "100g");
        cadastroComidas.cadastrarComida("Beef", "140g");
        cadastroComidas.cadastrarComida("Silver Fish", "100g");
        cadastroComidas.cadastrarComida("Silver Fish", "150g");
        cadastroComidas.cadastrarComida("Pasta", "150g");
        cadastroComidas.cadastrarComida("Tomatoes", "50g");
        cadastroComidas.cadastrarComida("Carrots", "40g");
        cadastroComidas.cadastrarComida("Broccoli", "40g");
        cadastroComidas.cadastrarComida("Apples", "150g");
        cadastroComidas.cadastrarComida("Strawberries","100g");
        cadastroComidas.cadastrarComida("Grapes","100g");
        cadastroComidas.cadastrarComida("Cheese ","30g");
        cadastroComidas.cadastrarComida("Cheese ","60g");
        cadastroComidas.cadastrarComida("Oats","30g");
    }

    public void CriarRefeicoes(ChoiceBox<String> escolha) throws ComidaNaoCadastradaException, DietaNaoCadastradaException {
        CriarComidas();
        Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
        String selecter = escolha.getSelectionModel().getSelectedItem();

        switch (selecter){
            case "PERDA_DE_PESO":
                Refeicao cafeDaManhaPP = new Refeicao("Café da manhã-Perda de Peso");
                cafeDaManhaPP.addComida(repositorioComidas.buscarComida("Bread, Boston Brown", "80.0g"));
                cafeDaManhaPP.addComida(repositorioComidas.buscarComida("Eggs",  "100.0g"));
                cafeDaManhaPP.addComida((repositorioComidas.buscarComida("Cheese", "30.0g")));
                cafeDaManhaPP.addComida((repositorioComidas.buscarComida("Banana", "80.0g")));
                Refeicao almocoPP = new Refeicao("Almoço-Perda de Peso");
                almocoPP.addComida(repositorioComidas.buscarComida("Rice", "150.0g"));
                almocoPP.addComida(repositorioComidas.buscarComida("Beans", "100.0g"));
                almocoPP.addComida(repositorioComidas.buscarComida("Potatoes", "70.0g"));
                almocoPP.addComida(repositorioComidas.buscarComida("Chicken Breast", "100.0g"));
                almocoPP.addComida(repositorioComidas.buscarComida("Tomatoes", "50.0g"));
                almocoPP.addComida(repositorioComidas.buscarComida("Carrots", "40.0g"));
                Refeicao lancheDaTardePP = new Refeicao("Lanche da tarde-Perda de Peso");
                lancheDaTardePP.addComida(repositorioComidas.buscarComida("Apples", "150.0g"));
                lancheDaTardePP.addComida(repositorioComidas.buscarComida("Grapes", "100.0g"));
                lancheDaTardePP.addComida(repositorioComidas.buscarComida("Strawberries","100.0g"));
                Refeicao jantarPP = new Refeicao("Jantar-PerdaDePeso");
                jantarPP.addComida(repositorioComidas.buscarComida("Sweet Potatoes", "140.0g"));
                jantarPP.addComida(repositorioComidas.buscarComida("Silver Fish",  "100.0g"));

                List<Refeicao> listaDeRefeicoesPP = new ArrayList<>();
                listaDeRefeicoesPP.add(cafeDaManhaPP);
                listaDeRefeicoesPP.add(almocoPP);
                listaDeRefeicoesPP.add(lancheDaTardePP);
                listaDeRefeicoesPP.add(jantarPP);

                usuario.AdicionarListaADieta(repositorioDietas.RetornarNomeDaDieta(), listaDeRefeicoesPP);
                break;

            case "GANHO_DE_MASSA":
                Refeicao cafeDaManhaGM = new Refeicao("Café da manhã-Ganho de massa");
                cafeDaManhaGM.addComida(repositorioComidas.buscarComida("Bread", "140.0g"));
                cafeDaManhaGM.addComida(repositorioComidas.buscarComida("Eggs", "150.0g"));
                cafeDaManhaGM.addComida(repositorioComidas.buscarComida("Cheese", "60.0g"));
                cafeDaManhaGM.addComida(repositorioComidas.buscarComida("Banana", "80.0g"));
                Refeicao almocoGM = new Refeicao("Almoço-Ganho de massa");
                almocoGM.addComida(repositorioComidas.buscarComida("Rice", "250.0g"));
                almocoGM.addComida(repositorioComidas.buscarComida("Beans", "200.0g"));
                almocoGM.addComida(repositorioComidas.buscarComida("Beef", "140.0g"));
                almocoGM.addComida(repositorioComidas.buscarComida("Tomatoes", "50.0g"));
                almocoGM.addComida(repositorioComidas.buscarComida("Broccoli", "40.0g"));
                Refeicao lancheDaTardeGM = new Refeicao("Lanche da tarde-Ganho de massa");
                lancheDaTardeGM.addComida(repositorioComidas.buscarComida("Apples", "150.0g"));
                lancheDaTardeGM.addComida(repositorioComidas.buscarComida("Grapes", "100.0g"));
                lancheDaTardeGM.addComida(repositorioComidas.buscarComida("Banana", "80.0g"));
                lancheDaTardeGM.addComida(repositorioComidas.buscarComida("Strawberries", "100.0g"));
                lancheDaTardeGM.addComida(repositorioComidas.buscarComida("Oats", "30.0g"));
                Refeicao jantarGM = new Refeicao("Jantar-Ganho de massa");
                jantarGM.addComida(repositorioComidas.buscarComida("Pasta", "150.0g"));
                jantarGM.addComida(repositorioComidas.buscarComida("Chicken Breast", "150.0g"));

                List<Refeicao> listaDeRefeicoesGM = new ArrayList<>();
                listaDeRefeicoesGM.add(cafeDaManhaGM);
                listaDeRefeicoesGM.add(almocoGM);
                listaDeRefeicoesGM.add(lancheDaTardeGM);
                listaDeRefeicoesGM.add(jantarGM);

                usuario.AdicionarListaADieta(repositorioDietas.RetornarNomeDaDieta(), listaDeRefeicoesGM);
                break;

            case "MANUTENÇÃO":
                Refeicao cafeDaManhaM = new Refeicao("Café da manha-Manutenção");
                cafeDaManhaM.addComida(repositorioComidas.buscarComida("Bread, Boston Brown", "80.0g"));
                cafeDaManhaM.addComida(repositorioComidas.buscarComida("Eggs", "150.0g"));
                cafeDaManhaM.addComida(repositorioComidas.buscarComida("Cheese", "30.0g"));
                cafeDaManhaM.addComida(repositorioComidas.buscarComida("Banana", "80.0g"));
                Refeicao almocoM = new Refeicao("Almoço-Manutenção");
                almocoM.addComida(repositorioComidas.buscarComida("Rice", "200.0g"));
                almocoM.addComida(repositorioComidas.buscarComida("Beans", "100.0g"));
                almocoM.addComida(repositorioComidas.buscarComida("Silver Fish", "150.0g"));
                almocoM.addComida(repositorioComidas.buscarComida("Tomatoes", "50.0g"));
                almocoM.addComida(repositorioComidas.buscarComida("Broccoli", "40.0g"));
                almocoM.addComida(repositorioComidas.buscarComida("Carrots", "40.0g"));
                Refeicao lancheDaTardeM = new Refeicao("Lanche da tarde-Manutenção");
                lancheDaTardeM.addComida(repositorioComidas.buscarComida("Apples", "150.0g"));
                lancheDaTardeM.addComida(repositorioComidas.buscarComida("Grapes", "100.0g"));
                lancheDaTardeM.addComida(repositorioComidas.buscarComida("Banana", "80.0g"));
                cafeDaManhaM.addComida(repositorioComidas.buscarComida("Oats", "30.0g"));
                Refeicao jantarM = new Refeicao("Jantar-Manutenção");
                jantarM.addComida(repositorioComidas.buscarComida("Sweet Potatoes", "140.0g"));
                jantarM.addComida(repositorioComidas.buscarComida("Beef", "140.0g"));
                jantarM.addComida(repositorioComidas.buscarComida("Cheese", "30.0g"));

                List<Refeicao> listaDeRefeicoesM = new ArrayList<>();
                listaDeRefeicoesM.add(cafeDaManhaM);
                listaDeRefeicoesM.add(almocoM);
                listaDeRefeicoesM.add(lancheDaTardeM);
                listaDeRefeicoesM.add(jantarM);

                usuario.AdicionarListaADieta(repositorioDietas.RetornarNomeDaDieta(), listaDeRefeicoesM);
                break;

            default:
                break;
        }


    }
}
