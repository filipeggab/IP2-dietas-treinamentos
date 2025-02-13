package br.ufrpe.treinos_dietas;


import br.ufrpe.treinos_dietas.controllers.TelaDeLoginGUIController;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private Usuario usuario = new Usuario ();
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaDeLogin.fxml"));
        Parent root = loader.load();
        TelaDeLoginGUIController tela = loader.getController();
        tela.setUsuario(usuario);
        primaryStage.setTitle("Tela de Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
