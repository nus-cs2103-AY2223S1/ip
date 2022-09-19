package duke;

import javafx.application.Application;

/**
 * @author Jeffry Lum
 * https://se-education.org/guides/tutorials/javaFxPart4.html
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}