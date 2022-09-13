package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.command.Command;
import duke.tasklist.TaskList;

/**
 * The driving engine for Duke
 */
public class Duke {
    private static final Duke duke = new Duke();

    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    /**
     * Constructs an instance with the default file path
     */
    private Duke() {
        storage = new Storage();
        parser = new Parser();
        taskList = new TaskList();
        parseTasksFromStorage();
    }

    private void parseTasksFromStorage() {
        try {
            Scanner fileScanner = storage.getScannerForTasksFile();
            while (fileScanner.hasNextLine()) {
                Command parsedCommand = parser.parseUserCommand(fileScanner.nextLine());
                parsedCommand.execute(storage, taskList);
            }
            fileScanner.close();
        } catch (FileNotFoundException | CustomMessageException e) {
            System.out.println("No existing data was found");
        }
    }

    /**
     * Returns the single instance of {@code Duke}
     * @return The {@code Duke} instance
     */
    public static Duke getDukeInstance() {
        return duke;
    }

    /**
     * Returns Duke's response to user input
     * @param input The input string
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command parsedCommand = parser.parseUserCommand(input);
            String output = parsedCommand.execute(storage, taskList);
            return output + "\n";
        } catch (CustomMessageException e) {
            return e.getMessage();
        }
    }
}
