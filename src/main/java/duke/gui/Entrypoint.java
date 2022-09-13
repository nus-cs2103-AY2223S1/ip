package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
// Adapted from https://se-education.org/guides/tutorials/javaFxPart1.html
public class Entrypoint {
    /**
     * Executes the GUI version of the bot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
