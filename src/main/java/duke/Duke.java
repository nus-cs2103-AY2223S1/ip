/**
 * This class is the main program for running Duke.
 */

package duke;

import java.io.FileNotFoundException;

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
        new Duke().run();
    }

    /**
     * Runs the main program.
     */
    public void run() {
        String reply = "";
        Parser parser = new Parser();
        boolean isExit = false;
        ui.showWelcome();

        while(!isExit) {
            try {
                reply = ui.readCommand();
                Command c = parser.parse(taskList, reply, ui);
                taskList = c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
