package maria;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maria.ui.LandingPage;

/**
 * Represents the entry point of the program.
 */
public class Maria extends Application {

    /**
     * Starts the main user interface window.
     * @param stage The main stage provided by JavaFX
     */
    @Override
    public void start(Stage stage) {

        TaskManager taskManager = new TaskManager();

        LandingPage landingPage = new LandingPage(taskManager);
        Scene scene = new Scene(landingPage);
        stage.setScene(scene);
        stage.setTitle("Maria");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch();
    }

}
