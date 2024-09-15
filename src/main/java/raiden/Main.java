package raiden;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import raiden.ui.MainWindow;

/**
 * Represents a GUI for Raiden using FXML.
 */
public class Main extends Application {
    private static final double STAGE_MIN_WIDTH = 450;
    private static final double STAGE_MIN_HEIGHT = 690;
    private static final String TITLE = "Raiden Bot";

    private static Stage stage;
    private Raiden raiden = new Raiden();
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            Main.stage = stage;
            Main.stage.setTitle(TITLE);
            Main.stage.getIcons().add(new Image("/images/Icon.png"));
            Main.stage.setMinWidth(STAGE_MIN_WIDTH);
            Main.stage.setMinHeight(STAGE_MIN_HEIGHT);
            Main.stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRaiden(raiden);
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the stage.
     */
    public static void closeStage() {
        Main.stage.close();
    }
}
