package meowmeow;

import javafx.application.Application;
import meowmeow.Meowmeow;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(meowmeow.Main.class, args);
    }
}