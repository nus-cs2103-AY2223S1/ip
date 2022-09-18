package duke;

import java.util.Optional;
import java.util.Scanner;

import duke.command.Command;
import duke.tasklist.TaskList;

/**
 * The driving engine for Duke
 */
public class Duke {
    private static final Duke duke = new Duke();

    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs an instance with the default file path
     */
    private Duke() {
        storage = new Storage();
        taskList = new TaskList();
        parseTasksFromStorage();
    }
    /**
     * Returns the single instance of {@code Duke}
     *
     * @return The {@code Duke} instance
     */
    public static Duke getDukeInstance() {
        return duke;
    }

    /**
     * Parses the commands from the file pointed to by the Scanner
     * @param fileScanner The Scanner to read from
     * @param storage The {@code Storage} object to use
     * @param taskList The task list to add to
     */
    public static void handleScanner(Scanner fileScanner, Storage storage, TaskList taskList) {
        try {
            while (fileScanner.hasNextLine()) {
                Command parsedCommand = Parser.parseUserCommand(fileScanner.nextLine());
                parsedCommand.execute(storage, taskList);
            }
            fileScanner.close();
        } catch (CustomMessageException e) {
            System.out.println("Unable to parse existing data");
        }
    }

    /**
     * Returns Duke's response to user input
     *
     * @param input The input string
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command parsedCommand = Parser.parseUserCommand(input);
            String output = parsedCommand.execute(storage, taskList);
            return output + "\n";
        } catch (CustomMessageException e) {
            return e.getMessage();
        }
    }

    private void parseTasksFromStorage() {
        if (!storage.isCurrentTasksFilePresent()) {
            return;
        }
        Optional<Scanner> fileScanner = storage.getScannerForTasksFile();
        fileScanner.ifPresent((scanner) -> handleScanner(scanner, storage, taskList));
    }
}
