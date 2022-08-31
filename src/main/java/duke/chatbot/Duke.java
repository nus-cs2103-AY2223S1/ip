package duke.chatbot;

import java.io.FileNotFoundException;

import duke.chatbot.command.Command;
import duke.chatbot.command.InvalidInputCommand;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.storage.Storage;
import duke.chatbot.util.Parser;

/**
 * The main application logic class.
 * @author jq1836
 */
public class Duke {
    /** The storage which handles saving and loading of files containing list of tasks */
    private Storage storage;

    /** The runtime instance of the list of tasks */
    private TaskList taskList;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        try {
            storage = Storage.of("duke.txt");
            taskList = storage.getTaskList();
        } catch (InvalidInputException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves into storage the current state of the task list.
     */
    private void save() {
        storage.save(taskList);
    }

    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

    /**
     * Returns a string response from passing the user input through Duke's logic.
     * @param userInput A string that represents a user's input.
     * @return A string response from passing the user input through Duke's logic.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseCommand(userInput);
            command.initData(taskList);
            if (Command.isExit(command)) {
                save();
                exit();
            }
            return command.execute().getMessage();
        } catch (InvalidInputException e) {
            return new InvalidInputCommand().execute().getMessage();
        }
    }
}
