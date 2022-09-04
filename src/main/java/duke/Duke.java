package duke;

import java.io.*;
import javafx.application.Platform;

/**
 * Represents the main Duke class.
 *
 * @author Denzel Tan
 */
public class Duke {
    private TaskList tasks;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath path of the file to be used
     */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response to the user's input.
     *
     * @param input the user's input
     */
    String getResponse(String input) {
        if (input.equals("bye")) {
            Platform.exit();
            return Ui.sayGoodbye();
        }
        else {
            return Parser.parse(input, tasks);
        }
    }
}
