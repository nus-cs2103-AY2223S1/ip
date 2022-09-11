import duke.Storage;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Storage.initStorage();
        Application.launch(Main.class, args);
    }
}
