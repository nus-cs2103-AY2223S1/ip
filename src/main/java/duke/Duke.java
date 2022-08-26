package duke;

import duke.command.Command;

/**
 * The Duke program to manage the user's tasks.
 */
public class Duke {
    /** The user interface object of Duke. */
    private Ui ui;

    /** The storage object to handle saving and loading of tasks. */
    private Storage storage;

    /** The list of current tasks. */
    private TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the task manager Duke program.
     *
     */
    public void start() {
        ui.welcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                Command c = Parser.parseInput(userCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").start();
    }
}
