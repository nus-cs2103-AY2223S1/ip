package dukechatbot.duke;

import javafx.application.Application;

/**
 * This class serves as a workaround around classpath issues to launch the JavaFx Application.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
