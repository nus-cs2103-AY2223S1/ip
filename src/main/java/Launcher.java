import duke.Storage;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main driver class used to launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        Storage.initStorage();
        Application.launch(Main.class, args);
    }
}
