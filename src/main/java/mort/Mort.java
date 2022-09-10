package mort;

import mort.command.Command;
import mort.exception.MortException;
import mort.parser.Parser;
import mort.storage.Storage;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Main class for Mort application
 */
public class Mort {
    /** Contains methods that deal with loading and saving of tasks */
    private Storage storage;
    /** List of tasks */
    private TaskList tasks;
    /** Deals with interactions with user */
    private Ui ui;

    /**
     * Constructor to initialize Storage, TaskList and Ui.
     */
    public Mort() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (MortException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves the responses based on the user input.
     * @param input The user input.
     * @return The string response.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            Command c = Parser.parse(input);
            response.append(c.execute(tasks, ui, storage));
        } catch (MortException e) {
            response.append(ui.getError(e.getMessage()));
        } finally {
            return response.toString();
        }
    }

    /**
     * Retrieves the welcome message when the program first runs.
     * @return The welcome message.
     */
    public String welcome() {
        return ui.getWelcomeMessage();
    }
}
