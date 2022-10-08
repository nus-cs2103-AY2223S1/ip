package launcher;

import javafx.application.Application;
import main.Main;

/**
 * <h1>Launcher class</h1>
 * Starting point of the chat bot that launches the Duke class.
 */
public class Launcher {
    /**
     * Launches the application using the Duke class.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
