package duke;

import duke.commands.Command;
import duke.common.exceptions.DukeException;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.util.Pair;

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
     * @return A Pair object containing the response as a string and
     *         a boolean indicating if the response is an error message.
     */
    public Pair<String, Boolean> getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            return new Pair<>(response, false);
        } catch (DukeException e) {
            return new Pair<>(e.getMessage(), true);
        }
    }
}
