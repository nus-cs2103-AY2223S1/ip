//Class below reuse code from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modification.
package anya;

import javafx.application.Application;

/**
 * Represents a launcher class to workaround classpath issues.
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
