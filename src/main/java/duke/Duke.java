package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The Duke class is a personal chatbot assistant.
 */
public class Duke {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    private boolean isExit;

    private boolean hasLoadingError;
    private String loadingError;

    /**
     * Constructs a new Duke chatbot that loads and saves data
     * to a specified file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DEFAULT_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
            hasLoadingError = false;
        } catch (DukeException e) {
            tasks = new TaskList();
            hasLoadingError = true;
            loadingError = ui.showError(e.getMessage());
        }
    }

    /**
     * Checks if the latest command to Duke is an exit command.
     *
     * @return true if the latest command is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Gets Duke's response to a specified input.
     *
     * @param input The specified user input.
     * @return Duke's response to the specified input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Gets Duke's welcome message.
     *
     * @return Duke's welcome message.
     */
    public String getWelcome() {
        String response = ui.showWelcome();
        if (hasLoadingError) {
            response += "\n" + Ui.LINE + "\n" + loadingError;
        }
        return response;
    }
}
