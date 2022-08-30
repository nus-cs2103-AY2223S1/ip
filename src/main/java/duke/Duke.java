package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String loadingError;

    /**
     * Constructor for a Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
            loadingError = "";
        } catch (DukeException e) {
            tasks = new TaskList();
            loadingError = e.getMessage();
        }
    }

    /**
     * Returns the loading error while trying to retrieve the tasks.
     * @return The loading error or an empty string if the tasks are loaded successfully.
     */
    public String showLoadingError() {
        return loadingError;
    }

    /**
     * Returns the greeting message to be shown to the user when the application first launches.
     * @return The greeting message.
     */
    public String greetUser() {
        return ui.showGreeting();
    }

    /**
     * Generates a response to the user input.
     * @param input The user input.
     * @return A string representation of the response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
