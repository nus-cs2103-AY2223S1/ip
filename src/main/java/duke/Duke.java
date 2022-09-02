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

    private boolean isExit = false;

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
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserInput();
                String dukeResponse = parser.parse(fullCommand, tasks);
                storage.saveData(tasks);
                isExit = parser.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        ui.exit();
    }


    public String getResponse(String userInput) {
        String dukeResponse;
        try {
            dukeResponse = parser.parse(userInput, tasks);
            storage.saveData(tasks);
            isExit = parser.isExit();
            if (isExit) {
                dukeResponse = ui.exit();
            }
            return dukeResponse;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
