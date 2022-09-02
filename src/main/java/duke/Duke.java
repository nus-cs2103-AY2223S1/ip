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
        tasks = retrieveTasks();
        assert this.tasks != null : "Tasks should not be null after being loaded.";
        commandsHistoryPointer = 0;
        commandsHistory = new ArrayList<>();
    }

    /**
     * Retrieve tasks from storage.
     * Returns empty list if fail to load from storage.
     *
     * @return Tasks from storage or empty list.
     */
    private TaskList retrieveTasks() {
        TaskList tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return tasks;
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

    private void addToCommandHistory(String input) {
        commandsHistory.add(input);
        // Set the command history pointer to point to the newest command.
        commandsHistoryPointer = commandsHistory.size();
    }

    private CommandResult parseAndExecuteInput(String input) throws DukeException {
        Command command = parser.parseCommand(input);
        assert command != null : "Command returned from parseCommand should never be null.";
        command.setData(tasks);
        return command.execute();
    }

    private void exitIfRequired(CommandResult result) {
        if (!result.shouldExit()) {
            return;
        }
        Platform.exit();
    }

    private void updateFileIfRequired(CommandResult result) throws IOException {
        if (!result.shouldUpdateFile()) {
            return;
        }
        storage.save(tasks);
    }

    private String handleUserInput(String input) {
        try {
            CommandResult result = parseAndExecuteInput(input);
            assert result != null : "Result from the execution of a command should never be null.";
            exitIfRequired(result);
            updateFileIfRequired(result);
            return result.getUserMessage();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from the Duke application based on a particular input.
     *
     * @param input User input provided.
     * @return Response or error message from the Duke application.
     */
    public String getResponse(String input) {
        addToCommandHistory(input);
        return handleUserInput(input);
    }
}
