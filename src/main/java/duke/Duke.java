package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The Duke class is a personal chatbot assistant.
 */
public class Duke {
    /** The Storage that loads and writes files for Duke. */
    private final Storage storage;
    /** The Ui that interacts with the user. */
    private final Ui ui;
    /** The list of tasks stored by Duke. */
    private TaskList tasks;

    /**
     * Constructs a new Duke chatbot that loads and saves data
     * to a specified file path.
     *
     * @param filePath The specified file path for Duke to access.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts the Duke chatbot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
