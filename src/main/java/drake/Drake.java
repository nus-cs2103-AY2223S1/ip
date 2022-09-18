package drake;

import java.io.IOException;

import drake.gui.Main;
import javafx.application.Application;

/**
 * Entrypoint for the Drake to-do list chatbot.
 */
public class Drake {

    private Drake() throws IOException, DrakeException {
        Storage storage = new Storage();
        new TaskList(storage.fileToList());
    }

    /**
     * Entrypoint for app.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
