package duke.gui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        javafx.application.Application.launch(duke.Main.class, args);
    }
}


