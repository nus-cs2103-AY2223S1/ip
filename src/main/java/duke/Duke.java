package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;


/**
 * Creates a chatbot named Duke.
 */
public class Duke {

    /** TaskList to handle all tasks related operations. */
    private TaskList taskList;

    /** Storage to handle file reading and writing. */
    private Storage storage;

    /** Ui that handles all interaction with the user. */
    private Ui ui;

    /**
     * Constructor for a Duke chatbot.
     *
     * @param filePath The file path to read the file from.
     */
    public Duke(String filePath) {
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        this.storage.readFromFile(this.taskList.getList());
        this.ui = new Ui(this.taskList);
    }

    /**
     * Returns the start message of Duke.
     *
     * @return The start message of Duke.
     */
    public String startMessage() {
        return ui.startMessage();
    }

    /**
     * Saves the current tasks to the file.
     */
    public void saveTasks() {
        storage.writeToFile(this.taskList.getList());
    }

    /**
     * Returns the exit message of Duke.
     *
     * @return The exit message of Duke.
     */
    public String exitMessage() {
        return ui.exitMessage();
    }

    /**
     * Gets the response from Duke by taking in a input from the user.
     *
     * @param input The input command from the user.
     * @return The response from Duke to be displayed.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Error in parsing command";
            c.execute(taskList, ui);
            assert !c.response().equals("") : "error in response";
            return c.response();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
