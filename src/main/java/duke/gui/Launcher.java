package duke.gui;

import javafx.application.Application;

/**
 * A class to launch the Main class.
 * Exists to work around classpath issues.
 * Adapted from https://se-education.org/guides/tutorials/javaFxPart1.html
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
