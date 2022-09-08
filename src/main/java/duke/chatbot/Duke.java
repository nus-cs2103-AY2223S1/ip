package duke.chatbot;

import java.io.FileNotFoundException;

import duke.chatbot.command.Command;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.storage.Storage;
import duke.chatbot.util.Parser;

/**
 * The main application logic class.
 *
 * @author jq1836
 */
public class Duke {
    /**
     * The storage which handles saving and loading of files containing list of tasks.
     */
    private Storage storage;

    /**
     * The runtime instance of the list of tasks.
     */
    private TaskList tasks;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        try {
            storage = Storage.from("duke");
            tasks = storage.getTasks();
        } catch (InvalidInputException | FileNotFoundException e) {
            exit();
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Switches to a different storage and reloads list of tasks.
     *
     * @param storage The storage to switch to.
     */
    public void setStorage(Storage storage) throws InvalidInputException {
        this.storage = storage;
        try {
            tasks = storage.getTasks();
        } catch (InvalidInputException | FileNotFoundException e) {
            throw new InvalidInputException("1");
        }
    }

    /**
     * Saves into storage the current state of the task list.
     */
    private void save() {
        storage.save(tasks);
    }

    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

    /**
     * Runs the instance of {@link Command} and returns the resultant message.
     *
     * @param command The command to be run.
     * @return A string that represents the resultant message.
     * @throws InvalidInputException If the command arguments are invalid.
     */
    private String runCommand(Command command) throws InvalidInputException {
        assert (tasks != null && storage != null);
        if (command.isExitCommand()) {
            exit();
        }
        command.initCommand(this);
        return command.execute().getMessage();
    }

    /**
     * Returns a string response from passing the user input through Duke's logic.
     *
     * @param userInput A string that represents a user's input.
     * @return A string response from passing the user input through Duke's logic.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseCommand(userInput);
            String response = runCommand(command);
            save();
            return response;
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }
}
