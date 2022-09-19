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
     * Launches the Duke program.
     *
     * @param args Command Line arguments.
     */
    public static void main(String[] args) {
        new Launcher().run();
    }
}
