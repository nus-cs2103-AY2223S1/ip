package scottie;

import java.util.Arrays;

import javafx.application.Application;

/**
 * The launcher class is used to start the Scottie application.
 */
public class Main {
    /**
     * Starts the Scottie application.
     * By default, it launches the GUI application.
     * If the "-cli" flag is specified, it launches the CLI
     * version of the application instead.
     */
    public static void main(String[] args) {
        if (Arrays.asList(args).contains("-cli")) {
            new Scottie().run();
        } else {
            Application.launch(MainApp.class, args);
        }
    }
}
