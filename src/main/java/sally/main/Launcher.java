package sally.main;

import javafx.application.Application;

/**
 * Launcher class to workaround classpath issues.
 *
 * @author liviamil
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
