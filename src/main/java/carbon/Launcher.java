package carbon;

import carbon.gui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the GUI application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
