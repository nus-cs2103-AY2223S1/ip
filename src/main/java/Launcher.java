import duke.Gui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
