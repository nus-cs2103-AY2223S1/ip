package maria;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maria.ui.LandingPage;

/**
 * Launches the JavaFX application.
 */
public class Launcher extends Application {

    /**
     * Launches the JavaFX application.
     *
     * This is to be called from the main class.
     */
    public void launch() {
        Application.launch();
    }

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

}
