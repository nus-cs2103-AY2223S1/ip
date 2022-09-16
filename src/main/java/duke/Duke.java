package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import javafx.stage.Stage;

/**
 * An interactive ChatBot that provides functions of a to-do list
 * depending on the user's input.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to create an instance of Duke.
     *
     * @param filePath String representation of the relative file path to store the data
     */
    public Duke(String filePath, Stage stage) {
        ui = new Ui(stage);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a response from the Chat Bot after user inputs a command.
     * @param fullCommand String command that user inputs
     * @return String representing the response of the Chat Bot
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
