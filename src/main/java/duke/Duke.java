package duke;

import java.io.FileNotFoundException;

/**
 * This class is the main program for running Duke.
 */
public class Duke {

    /** The tasklist to store the tasks */
    private TaskList taskList;

    /** The storage for writing and loading from the file */
    private Storage storage;

    /** The ui to handle user interactions */
    private Ui ui;

    /**
     * Creates a new instance of the Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DukeConstants.FILENAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError("file not found!");
            taskList = new TaskList();
        }

    }

    public static void main(String[] args) {
       // new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();

        try {
            Command c = parser.parse(taskList, input, ui);
            String reply = c.execute(taskList, ui, storage);
            return reply;

        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }


}
