package duke;

import javafx.application.Application;

public class Launcher {
    /**
     * Executes main program.
     */
    public void run() {
        Application.launch(Ui.class);
    }

    /**
     * Main method for Duke.
     *
     * @param args Command Line arguments.
     */
    public static void main(String[] args) {
        new Launcher().run();
    }
}
