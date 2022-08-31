package duke;

import duke.core.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Nephelite
 * @version 0.2
 */
public class DukeLauncher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
