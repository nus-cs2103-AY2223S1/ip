package duke;

import duke.command.Command;
import duke.exception.DukeException;


/**
 * Main file of Duke to be run to initiate the program.
 */
public class Duke {
    //Backend variables
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke instance and load the previous stored TaskList from the filePath.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("mytasklist.txt");
        try {
            taskList = new TaskList(storage.load());
            if (taskList.isEmpty()) {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Initialise the Duke program.
     */
    public void run() {
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(taskList, ui, storage);
            return response;
        } catch (DukeException e) {
            return this.ui.getError(e);
        }
    }

}
