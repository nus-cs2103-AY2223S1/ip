package duke;

import commands.Command;
import exceptions.DukeException;

/**
 * Main class for chatbot.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new chatbot which uses some file path as a way to store tasks.
     *
     * @param filePath The specified file path.
     */
    Duke(String filePath) {
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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Starts the chatbot by reading user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException exception) {
                ui.showError(exception.toString());
            } finally {
                ui.showLine();
            }
        }
    }
}
