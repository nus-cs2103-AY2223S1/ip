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
            taskList = new TaskList();
        }
    }

    /**
     * Returns Duke's response to the user's input.
     *
     * @param input Input that the user entered into the text box.
     * @return The response from Duke based on the given input.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parseInput(input);
            if (command.canExit()) {
                Platform.exit();
            }
            return command.execute(storage, taskList, ui);
        } catch (DukeException e) {
            return ui.printErrorMessage(e);
        }
    }
}
