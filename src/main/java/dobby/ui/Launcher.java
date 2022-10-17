package dobby.ui;

import dobby.Main;
import javafx.application.Application;

/**
 * Launches Duke.
 * Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">se-education</a>
 */
public class Launcher {
    /**
     * Main function that launches the javaFX scenes
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
        System.exit(0);
    }
}
