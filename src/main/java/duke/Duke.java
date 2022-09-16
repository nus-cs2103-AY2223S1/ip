package duke;

import command.Command;
import task.TaskList;

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

    private static boolean hasFile = false;

    /**
     * Creates a new instance of the Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DukeConstants.FILENAME_DATA, DukeConstants.FILENAME_ARCHIVE);
        try {
            taskList = new TaskList(storage.loadData());
            hasFile = true;
        } catch (FileNotFoundException e) {
            hasFile = false;
            taskList = new TaskList();
            assert taskList.getSize() == 0 : "Tasks should be 0";
        }

    }

    public static void main(String[] args) {
       // new Duke().run();
    }

    /**
     * Gets response to user input.
     *
     * @param input User input.
     * @return Response to display.
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

    public static boolean getFileStatus() {
        return hasFile;
    }


}
