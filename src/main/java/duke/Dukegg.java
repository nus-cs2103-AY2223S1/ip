package duke;

import commands.Command;
import exceptions.DukeException;

/**
 * Main class for chatbot.
 */
public class Dukegg {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new chatbot which uses some file path as a way to store tasks.
     *
     * @param filePath The specified file path.
     */
    public Dukegg(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList currentTasks;
        try {
            currentTasks = new TaskList(storage.load());
        } catch (DukeException exception) {
            ui.showLoadingError();
            currentTasks = new TaskList();
        }
        this.tasks = currentTasks;
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException exception) {
            return ui.showError(exception.toString());
        }
    }

    public String getGreetingMessage() {
        return this.ui.showWelcome();
    }
}
