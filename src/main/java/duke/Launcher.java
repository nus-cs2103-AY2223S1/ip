package duke;

import duke.gui.Main;

import javafx.application.Application;

/**
 * Launcher for Duke application including GUI.
 *
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html#using-fxml-in-our-application
 * with minor modifications.
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
