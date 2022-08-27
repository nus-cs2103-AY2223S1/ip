package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * Represents the Duke application.
 * Responsible for user interaction.
 */
public class Duke {
    private final Parser parser;
    private final Storage storage;
    private final TaskList tasks;
    /* Index of current command history location. */
    private int commandsHistoryPointer;
    /* History of commands. */
    private final ArrayList<String> commandsHistory;

    /**
     * Constructor for a Duke application instance.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage("data", "data/tasks");

        TaskList tasks;
        try {
            // Attempt to load tasks from storage.
            tasks = storage.load();
        } catch (DukeException e) {
            e.printStackTrace();
            // Load empty list if fail to load from storage.
            tasks = new TaskList();
        }
        this.tasks = tasks;
        commandsHistoryPointer = 0;
        commandsHistory = new ArrayList<>();
    }

    /**
     * Gets the previous command and moves the pointer of the history up by 1.
     * If history is empty, null is returned.
     *
     * @return Previous command or null.
     */
    public String getPreviousCommand() {
        if (commandsHistory.isEmpty()) {
            return null;
        }
        commandsHistoryPointer = Math.max(0, commandsHistoryPointer - 1);
        return commandsHistory.get(commandsHistoryPointer);
    }

    /**
     * Gets the next command and moves the pointer of the history down by 1.
     * If history is empty, null is returned.
     *
     * @return Next command or null.
     */
    public String getNextCommand() {
        if (commandsHistory.isEmpty()) {
            return null;
        }
        commandsHistoryPointer = Math.min(commandsHistory.size(), commandsHistoryPointer + 1);
        if (commandsHistoryPointer == commandsHistory.size()) {
            // Pointing to new "empty" command.
            return "";
        }
        return commandsHistory.get(commandsHistoryPointer);
    }

    /**
     * Gets the response from the Duke application based on a particular input.
     *
     * @param input User input provided.
     * @return Response from the Duke application.
     */
    public String getResponse(String input) {
        commandsHistory.add(input);
        // Set the command history pointer to point to the newest command.
        commandsHistoryPointer = commandsHistory.size();

        try {
            Command command = parser.parseCommand(input);
            // Populate command with tasks.
            command.setData(tasks);
            CommandResult result = command.execute();
            if (result.shouldExit()) {
                Platform.exit();
            }
            if (result.shouldUpdateFile()) {
                // Save to storage.
                storage.save(tasks);
            }
            return result.getUserMessage();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
