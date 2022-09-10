package mia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        final Mia mia = new Mia("data/Mia.txt");
        mia.run();
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, world!");
        Scene scene = new Scene(label);

        stage.setTitle("Mia");
        stage.setScene(scene);
        stage.show();
    }
}
