package fred;

import commands.Command;
import exception.FredException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Fred is a chat bot that helps users to maintain a task list for future reference.
 * Tasks in the task list can be marked as done or not done.
 */
public class Fred {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Create a new Fred object
     * @param filePath filepath to data file which stores Fred's data
     */
    public Fred(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FredException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);

            if (c.isExit()) {
                System.exit(0);
            }

            return ui.getMessage();
        } catch (FredException e) {
            return e.getMessage();
        }
    }
}
