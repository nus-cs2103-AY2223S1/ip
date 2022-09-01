package Rabbit;

import java.io.IOException;

import Rabbit.RabbitException.ImportDataException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import Rabbit.GUI.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Rabbit rabbit;

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

        try {
            rabbit = new Rabbit();
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRabbit(rabbit);
            stage.show();
            fxmlLoader.<MainWindow>getController().showText(rabbit.GREET);
            fxmlLoader.<MainWindow>getController().showText(rabbit.INSTRUCTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImportDataException e) {
            fxmlLoader.<MainWindow>getController().showText(e.toString());
        }
    }
}
