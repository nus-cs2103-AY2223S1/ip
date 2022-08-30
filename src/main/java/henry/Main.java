package henry;

import java.io.IOException;

import components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Driver class for Henry
 */
public class Main extends Application {

    private final Henry henry = new Henry();

    /**
     * henry.Main method in which a Henry instance is created.
     *
     * @param args standard command line arguments, not
     *             applicable to this program.
     */
    public static void main(String[] args) {
        Henry henry = new Henry();
        henry.runProgram();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHenry(henry);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
