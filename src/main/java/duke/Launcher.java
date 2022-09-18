package duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    // Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
