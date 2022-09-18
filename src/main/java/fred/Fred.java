package fred;

import java.net.URI;

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

    private String welcomeMessage;

    /**
     * Create a new Fred object
     * @param filePath filepath to data file which stores Fred's data
     */
    public Fred(URI filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        welcomeMessage = "Hello! I'm Fred! What can I do for you?";
        try {
            tasks = new TaskList(storage.load());
            welcomeMessage = "Data file loaded.\n" + welcomeMessage;
        } catch (FredException e) {
            welcomeMessage = e.getMessage() + welcomeMessage;
            tasks = new TaskList();
        }
    }

    /**
     * Get response from Fred from the given input.
     * @param input String which contains the input from the user
     * @return String which contains Fred's response
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);

            if (c.isExit()) {
                System.exit(0);
            }

            String message = ui.getMessage();
            assert(!message.isBlank());;

            return message;
        } catch (FredException e) {
            return e.getMessage();
        }
    }

    /**
     * Return Fred's welcome message.
     * @return Returns a string containing Fred's welcome message
     */
    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
