package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The Duke class is the main class that handles the operation of the task manager.
 *
 * @author Edric Yeo
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke instance based on a given data file.
     *
     * @param filePath The filePath of the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showError("Error Loading Storage File!");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke task manager. Takes in user input and performs tasks accordingly.
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
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Creates a new task manager based on a given filePath, and runs the task manager.
     */
    public static void main(String[] args) throws DukeException, IOException {
        String filePath = "data/duke.txt";
        new Duke(filePath).run();
    }

    /**
     * Returns a response to user input.
     *
     * @param input The user input.
     * @return A String containing the response to the given user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
