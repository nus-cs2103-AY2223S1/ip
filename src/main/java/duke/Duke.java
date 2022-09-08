package duke;

import java.io.IOException;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the chatbot and how it facilitates the interactions between
 * the user and the chatbot.
 */
public class Duke {

    private static String welcome = "Hello! I'm Seaward,\n"
            + "your friendly neighbourhood chatbot.\n"
            + "Type something and I will reply!";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Returns a Duke object which takes in a string that represents the file path that contains the
     * tasks of the user which is then passed into the storage object.
     * @param filePath A string representing the file path that stores the tasks.
     */
    public Duke(String filePath) {
        // Deals with interactions with the user
        // Most likely will be using Scanner
        // Returns messages/error messages to the user
        ui = new Ui();

        // Deals with loading tasks from the file and
        // saving them in the file
        storage = new Storage(filePath);

        // Loading the tasks in the file to the taskList
        tasks = new TaskList(storage.load());

        // Initialises the parser to read user commands
        parser = new Parser(tasks, storage, ui);
    }

    /**
     * Returns a welcome message that greets the user when the user runs the chatbot.
     * @return A welcome message.
     */
    public static String getWelcome() {
        return welcome;
    }

    /**
     * Returns an appropriate response from the parser according to the user input.
     * @param input A string that represents the user input.
     * @return A string that contains a success or failure message.
     */
    public String getResponse(String input) {
        try {
            String response = parser.readInputString(input);
            return response;
        } catch (InvalidDescriptionException | InvalidCommandException | IOException e) {
            return e.getMessage();
        }
    }
}
