package br.ufrpe.treinos_dietas;


import br.ufrpe.treinos_dietas.dados.RepositorioDietas;
import br.ufrpe.treinos_dietas.dados.RepositorioPlanoDeTreino;
import br.ufrpe.treinos_dietas.dados.RepositorioUsuarios;
import br.ufrpe.treinos_dietas.exceptions.ExercicioNaoCadastradoException;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public Usuario usuario = SessaoUsuario.getInstancia().getUsuario();
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    RepositorioDietas repositorioDietas = new RepositorioDietas();
    RepositorioPlanoDeTreino repositorioPlanoDeTreino = new RepositorioPlanoDeTreino();

    public void start(Stage primaryStage) throws IOException, ExercicioNaoCadastradoException {

        URL fxmlLocation = getClass().getClassLoader().getResource("fxml/TelaDeLogin.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        primaryStage.setTitle("App dietas e Treinos");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            SessaoUsuario.getInstancia().limparSessao();
            // Salvar os dados antes de fechar o programa
            RepositorioUsuarios.getInstance().salvarUsuarios();
            primaryStage.close();
        });
    }

    public static void main(String[] args) {launch(args);
    }
}
