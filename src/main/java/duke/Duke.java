package duke;

import command.Command;

/**
 * The main class that runs the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes a Duke object with the specified file path to load and store tasks.
     *
     * @param filePath The file path to the local file responsible for loading and saving.
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadLocalData());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    /**
     * Runs the Duke program.
     */

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

