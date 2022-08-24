package duke;

import duke.commands.Command;
import duke.task.TaskList;

/**
 * The Duke chatbot
 */
public class Duke {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a duke chatbot which persists tasks to the file named fileName.
     * @param fileName Name of the file to store tasks in.
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
     * Executes the duke task chat bot.
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
                isExit = c.getShouldExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
