package jude;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author cheeheng-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor
//modifications.

/**
 * A GUI for Jude the chatbot using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_GUI_DATA_STORAGE_PATH = "data/tasks.txt";
    private static final String MAIN_WINDOW_TITLE = "Jude the chatbot";
    private static final double MIN_WIDTH = 300.0;
    private static final double MIN_HEIGHT = 500.0;
    private Jude jude = new Jude(DEFAULT_GUI_DATA_STORAGE_PATH);

    /**
     * Creates a new GUI instance for Jude the chatbot.
     *
     * @throws IOException When system I/O fails.
     */
    public Main() throws IOException {
    }

    /**
     * Creates a new GUI instance for Jude the chatbot, where the data is stored in the given
     * filepath.
     *
     * @param filePath The storage directory of the task tracker data.
     * @throws IOException When system I/O fails.
     */
    public Main(String filePath) throws IOException {
        jude = new Jude(filePath);
    }

    @Override
    public void start(Stage stage) {
        assert(stage != null);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "Root node of MainWindow cannot be null";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            fxmlLoader.<MainWindow>getController().setJude(jude);

            //@@author cheeheng-reused
            // Reused from https://stackoverflow.com/questions/29055792/javafx-window-settitle
            // with minor modifications.
            stage.setTitle(MAIN_WINDOW_TITLE);
            //@@author

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs Jude the chatbot on console mode.
     *
     * @throws IOException When system I/O fails.
     */
    public void runConsole() throws IOException {
        jude.run();
    }
}

//@@author
