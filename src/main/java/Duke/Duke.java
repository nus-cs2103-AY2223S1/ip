/**
 * Main class
 */
package Duke;

import Command.Command;

import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor that initialises the relative file path to save / load
     * the specific file
     *
     * @param filePath which contains the relative path of the saved task list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
    }

    /**
     * Continuously run the program and executes the task input by the user
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DateTimeParseException e) {
                ui.showError(Constants.INVALID_DATE);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
