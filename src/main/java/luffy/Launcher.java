package luffy;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to Launch Application.
     *
     * @param args User commandline input
     */
    public static void main(String[] args) {
        Application.launch(Luffy.class, args);
    }
}
