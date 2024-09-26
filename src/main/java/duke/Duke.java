package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exceptions.DukeException;

/**
 * Represents the main entry of the Duke GUI.
 * A <code>Duke</code> object corresponds to
 * a collection of Ui, taskList and storage
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Duke object consist of Ui, taskList and storage
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.storage.isCreated();
        try {
            this.storage.load(this.taskList);
        } catch (FileNotFoundException e) {
            System.out.println("PLEASE RESTART DUKE");
        } catch (DukeException e) {
            System.out.println("CORRUPTED DATA");
        }
    }

    /**
     * Returns string output of duke.
     *
     * @param input user input.
     * @return String.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.ui, this.taskList, this.storage);

        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
