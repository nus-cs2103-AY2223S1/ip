package cheese;

import javafx.application.Application;

/**
 * Launches the application. Acts as a workaround to classpath issues.
 */
public class Launcher {

    /**
     * Acts as entry point to application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(cheese.ui.Main.class, args);
    }
}
