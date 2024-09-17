package doris;

import exception.DorisException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    private Doris doris;
    {
        try {
            doris = new Doris("data/tasks.txt");
        } catch (DorisException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDoris(doris);
            fxmlLoader.<MainWindow>getController().welcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
