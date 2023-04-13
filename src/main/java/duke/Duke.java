package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Parser;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Represents a chat-bot that can be used to manage
 * and store a list of tasks for users.
 */
public class Duke {
    private TaskStorage storage;
    private TaskList taskList;
    private Ui ui;

    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new TaskStorage(filePath);
        taskList = storage.loadTask();
    }

    /**
     * Shows users that the bot is activated successfully
     * Reads input from the user and initialize the response
     * object to handle this input.
     */
    public String run(String input) {
        try {
            ArrayList<String> parsedInput = new Parser().parse(input);
            String response = new Command(storage, taskList, ui).execute(parsedInput);
            assert response != null : "Response should not be null";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
