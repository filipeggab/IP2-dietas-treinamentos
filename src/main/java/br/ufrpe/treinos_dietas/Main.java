package br.ufrpe.treinos_dietas;


import br.ufrpe.treinos_dietas.controllers.TelaDeLoginGUIController;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.SessaoUsuario;
import br.ufrpe.treinos_dietas.negocio.beans.usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {
    public Usuario usuario = SessaoUsuario.getInstancia().getUsuario();

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

        primaryStage.setOnCloseRequest(event -> {
            SessaoUsuario.getInstancia().limparSessao();
            primaryStage.close();
        });
    }

    public static void main(String[] args) {
        serializar(new Usuario("nome", "1234"));

        deserializar();

        launch(args);

    }

    public static void serializar (Usuario usuario){
        System.out.println("serializar");
        Path path = Paths.get("usuario.data");
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
            oos.writeObject(usuario);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void deserializar (){
        Path path = Paths.get("usuario.data");
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){
            Usuario usuario = (Usuario) ois.readObject();
            System.out.println(usuario);
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
