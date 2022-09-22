package mia;

import java.io.IOException;

import general.ui.MainGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Mia mia = new Mia("data/Mia.txt");

    public static void main(String[] args) {
        mia.run();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainGui.fxml"));
            fxmlLoader.setController(new MainGui());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainGui>getController().setContext(mia);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
