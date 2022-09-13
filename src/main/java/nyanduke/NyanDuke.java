package nyanduke;

import nyanduke.command.Command;
import nyanduke.task.TaskList;

/**
 * The NyanDuke class is a personal chatbot assistant.
 */
public class NyanDuke {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    private boolean isExit;

    private boolean hasLoadingError;
    private String loadingError;

    /**
     * Constructs a new NyanDuke chatbot that loads and saves data
     * using a default file path.
     */
    public NyanDuke() {
        ui = new Ui();
        storage = new Storage(DEFAULT_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
            hasLoadingError = false;
        } catch (NyanDukeException e) {
            tasks = new TaskList();
            hasLoadingError = true;
            loadingError = ui.showError(e.getMessage());
        }
    }

    /**
     * Checks if the latest command to NyanDuke is an exit command.
     *
     * @return true if the latest command is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Gets NyanDuke's response to a specified input.
     *
     * @param input The specified user input.
     * @return NyanDuke's response to the specified input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (NyanDukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Gets NyanDuke's welcome message.
     *
     * @return NyanDuke's welcome message.
     */
    public String getWelcome() {
        String response = ui.showWelcome();
        if (hasLoadingError) {
            response += "\n" + Ui.LINE + "\n" + loadingError;
        }
        return response;
    }
}
