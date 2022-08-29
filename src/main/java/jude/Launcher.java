package jude;

//@@author cheeheng-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart1.html with minor modifications
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the GUI.
     *
     * @param args Not user for now.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
