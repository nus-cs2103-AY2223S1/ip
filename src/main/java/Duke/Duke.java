package Duke;

import Command.Command;

import java.time.format.DateTimeParseException;

/**
 * Main class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor that initialises the relative file path to save / load
     * the specific file
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(Constants.FILE_PATH);
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

    /**
     * Returns a string that will be printed in the UI after the user
     * keys in a specific command
     *
     * @param command
     * @return string that prints the final output to the UI
     */
    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
