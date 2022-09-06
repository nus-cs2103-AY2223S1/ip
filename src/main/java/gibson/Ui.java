package gibson;

import java.io.IOException;

import gibson.task.Parser;
import gibson.view.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Ui prints strings in a specific format for the Gibson program.
 */
public class Ui extends Application {
    private Parser parser = new Parser();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Gibson");
            fxmlLoader.<MainWindow>getController().setUi(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns response of the program from a user input
     * Allows other classes to communicate with the parser.
     * @param input the user input
     * @return the response of the program from a user input
     */
    public String getResponse(String input) {
        return parser.processInput(input);
    }
}
