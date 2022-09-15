package duke.gui;

import javafx.application.Application;

//@@author leehuiyulaura-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart1.html
// with minor modifications
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the main class of the application.
     *
     * @param args Arguments to launch the main class.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
//@@author
