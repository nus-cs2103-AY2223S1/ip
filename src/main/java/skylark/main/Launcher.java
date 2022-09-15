package skylark.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Represents the main entry point of the Skylark program. <br><br>
     * Creates an application view and show a GUI to the user.
     *
     * @param args Command-line parameters that can be passed into Skylark.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
