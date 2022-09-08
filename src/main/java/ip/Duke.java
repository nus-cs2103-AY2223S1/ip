package ip;

import java.io.IOException;

import ip.command.DukeCommand;
import ip.exception.DukeException;
import ip.utility.Parser;
import ip.utility.Storage;
import ip.utility.TaskList;


/**
 * Task management program.
 */
public class Duke {
    /** Extracts command from string inputs */
    private static Parser parser;
    /** List of all tasks in Duke */
    private static TaskList taskList;
    /** Loads and saves task information to hard drive */
    private static Storage storage;

    /**
     * Constructor for Duke.
     *
     * @param dataFilePath Path of file to load task data from.
     */
    public Duke(String dataFilePath) {
        storage = new Storage(dataFilePath);
        parser = new Parser();
        try {
            taskList = storage.getTaskList();
        } catch (IOException e) {
            System.out.println("Error in loading file from specified path.");
            System.out.println("Duke will not save any task data this run.");
            // Initialize new empty task list.
            taskList = new TaskList();
        }
    }

    /**
     * Replies to the user's input.
     *
     * @param userInput User's input.
     * @return Duke's reply.
     */
    public String getResponse(String userInput) {
        parser.loadUserInput(userInput);
        try {
            DukeCommand command = parser.getCommand();
            String response = command.execute(taskList);
            storage.saveTasks(taskList);
            return response;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
