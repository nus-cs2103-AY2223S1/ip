package duke;

import duke.commands.Command;
import duke.task.TaskList;

/**
 * The task Chat bot.
 */
public class Duke {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Duke.
     *
     * @param fileName the name of the file for saved tasks to be stored in.
     */
    public Duke(String fileName) {
        this.ui = new Ui();

        Storage storage;
        try {
            storage = new Storage(fileName);
        } catch (DukeException e) {
            ui.showLoadingError();
            storage = null;
        }
        this.tasks = new TaskList(storage);
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Runs the Duke chat bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
