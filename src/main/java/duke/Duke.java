package duke;

import duke.command.Command;

/**
 * Represents main class for the Duke programme
 *
 * @author benjytan45678
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object with its relevant filepath.
     *
     * @param filePath The file path to the local file responsible for storing and loading.
     */
    public Duke(String filePath) {
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
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
            } catch (DukeException e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point to the programme
     */
    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();
    }
}

