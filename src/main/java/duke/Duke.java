package duke;

import duke.command.Command;
import javafx.application.Platform;

/**
 * The main class for the Duke program.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object with specified file path to load and store tasks.
     *
     * @param filePath The file path to the local file responsible for loading and saving.
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parseInput(input);
            boolean isExit = command.canExit();
            if (isExit) {
                Platform.exit();
            }
            return command.execute(storage, taskList, ui);
        } catch (DukeException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
