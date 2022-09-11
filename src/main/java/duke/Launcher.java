package duke;

//@@author weiquany-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart1.html

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
