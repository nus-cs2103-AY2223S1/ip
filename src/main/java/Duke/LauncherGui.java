package Duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class LauncherGui {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}