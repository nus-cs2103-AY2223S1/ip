package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.Ui;


import javafx.application.Platform;



/**
 * Contains the logic for the Duke program
 */
public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Instantiates a new duke object that load previous saved task from
     * SavedTask.txt
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("SavedTask.txt");
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
        boolean isExit = false;
        if (!isExit) {
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
