package ekud.main;
import java.io.IOException;

import ekud.gui.controller.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * A GUI for Ekud using FXML.
 */
public class Main extends Application {
    private Ekud ekud = new Ekud();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Stage realStage = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setDuke(ekud);
            realStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit() {
        Platform.exit();
    }

}
