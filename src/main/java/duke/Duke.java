package duke;

import duke.task.TaskList;

/**
 * Represents a <code>Duke</code> programme.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Duke program.
     *
     * @param filePath directory to store task data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Runs the Duke program until user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserInput();
                parser.parse(fullCommand, tasks);
                storage.saveData(tasks);
                isExit = parser.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data").run();
    }
}
