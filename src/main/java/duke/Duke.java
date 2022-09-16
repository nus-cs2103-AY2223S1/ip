package duke;
import java.io.IOException;
import java.util.Objects;

import duke.command.Command;

/**
 * Tracks all objects.
 *
 * @author Lim Ai Lin
 */
public class Duke {

    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;

    /**
     * Creates a Duke object which saves all tasks into the filePath.
     *
     * @param filePath The text file to save the tasks.
     */
    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            tasks = STORAGE.load();
        } catch (IOException e) {
            UI.showLoadingError();
        }
    }

    /**
     * Runs the main program to understand the user input and form a response.
     *
     * @param input The string the user inputs to the chatbot.
     * @return The string to be displayed.
     */
    protected String run(String input) throws DukeException {
        if (!Objects.equals(input, "bye")) {
            Command c = Parser.parse(input);
            return c.execute(tasks, UI, STORAGE);
        } else {
            return UI.exit();
        }

    }

    /**
     * Gets the response for the user input.
     *
     * @param input The string the user inputs to the chatbot.
     * @return The string to be displayed.
     */
    public String getResponse(String input) throws DukeException {
        assert !Objects.equals(input, "");
        return run(input);
    }
}
