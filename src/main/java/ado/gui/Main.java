package ado.gui;

import java.io.IOException;

import ado.Ado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Ado using FXML.
 */
public class Main extends Application {

    private Ado ado = new Ado("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            fxmlLoader.<MainWindow>getController().setAdo(ado);
            fxmlLoader.<MainWindow>getController().showWelcome();
            setUpStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates modifications to stage design
     * @param stage
     */
    public void setUpStage(Stage stage) {
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/app_icon.png")));
        stage.setTitle("Ado");
        stage.setResizable(false);
        stage.sizeToScene();
    }
}
