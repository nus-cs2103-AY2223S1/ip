package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The Duke class is a personal chatbot assistant.
 */
public class Duke {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    /** The Storage that loads and writes files for Duke. */
    private final Storage storage;
    /** The Ui that interacts with the user. */
    private final Ui ui;
    /** The list of tasks stored by Duke. */
    private TaskList tasks;
    private boolean isExit;

    /**
     * Constructs a new Duke chatbot that loads and saves data
     * to a specified file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DEFAULT_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
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
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return ui.getResponse();
        }
        return ui.getResponse();
    }

    /**
     * Gets Duke's welcome message.
     *
     * @return Duke's welcome message.
     */
    public String getWelcome() {
        ui.showWelcome();
        return ui.getResponse();
    }
}
