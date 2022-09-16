package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.Ui;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * Contains the logic for the Duke program
 */
public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Instantiates a new duke object that load previous saved task from
     * SavedTask.txt
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/Duke/SavedTask.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Instantiates a new duke object that load previous saved task from
     * a specified file path
     *
     * @param filePath file path that a user wants to load and save tasks objects to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }


    /**
     * Gets a response from a given input
     *
     * @param input input given by user
     * @return String response
     */
    public String getResponse(String input) {
        assert input != null : "user input cannot be null";
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(input);
                String output = c.execute(tasks, ui, storage);
                isExit = c.isExit();
                if (isExit) {
                    Platform.exit();
                    return output;
                }
                return output;
            } catch (DukeException e) {
                String output = ui.showLoadingError(e.getMessage());
                return output;
            }
        }
        return "";
    }
}
