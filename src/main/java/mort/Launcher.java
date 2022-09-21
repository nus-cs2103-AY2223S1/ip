package mort;

import javafx.application.Application;
import mort.ui.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    //@@author izzahaj-reused
    //Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
    // with minor modification
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    //@@author
}
