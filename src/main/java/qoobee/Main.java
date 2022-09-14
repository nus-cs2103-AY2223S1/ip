package qoobee;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Qoobee qoobee = new Qoobee();
    private static Stage stage;

    /**
     * Starts the stage when application launches.
     * @param stage The specified stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            Main.stage = stage;
            Main.stage.setScene(scene);
            Main.stage.setTitle("Qoobee Bot");
            Main.stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/fish.png")));
            fxmlLoader.<MainWindow>getController().setQoobee(qoobee);
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the application.
     */
    public static void closeStage() {
        Main.stage.close();
    }

}