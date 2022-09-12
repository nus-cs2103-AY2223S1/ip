package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.exceptions.NoUndoActionsException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.undo.UndoAction;
import javafx.application.Platform;

/**
 * Represents the Duke application.
 * Responsible for user interaction.
 */
public class Duke {
    private static final String WELCOME_MESSAGE = "Beep boop!\n"
            + "I'm your friendly neighbourhood bot, Little Duke!";
    // Delay is in milliseconds
    private static final int DELAY_ON_EXIT = 1500;

    private final Parser parser;
    private final Storage storage;
    private final TaskList tasks;
    /* Index of current command history location. */
    private int commandsHistoryPointer;
    /* History of commands. */
    private final ArrayList<String> commandsHistory;
    /* Actions to undo. */
    private final LinkedList<UndoAction> undoActions;

    /**
     * Constructs a Duke application instance.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage("data", "data/tasks");
        tasks = retrieveTasks();
        assert this.tasks != null : "Tasks should not be null after being loaded.";
        commandsHistoryPointer = 0;
        commandsHistory = new ArrayList<>();
        undoActions = new LinkedList<>();
    }

    /**
     * Retrieves tasks from storage.
     * If loading from storage fails, empty list is returned.
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
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, DELAY_ON_EXIT);
    }

    private void updateFileIfRequired(CommandResult result) throws IOException {
        if (!result.shouldUpdateFile()) {
            return;
        }
        storage.save(tasks);
    }

    private void addToUndoActions(CommandResult result) {
        UndoAction undoAction = result.getUndoAction();
        if (undoAction == null) {
            return;
        }
        undoActions.push(undoAction);
    }

    private void useUndoActionIfRequired(CommandResult result) throws DukeException {
        if (!result.shouldUndo()) {
            return;
        } else if (undoActions.isEmpty()) {
            throw new NoUndoActionsException();
        }
        UndoAction undoAction = undoActions.pop();
        assert undoAction != null : "Null action should not have been added to undoActions.";
        undoAction.perform(tasks);
    }

    private String handleUserInput(String input) {
        try {
            CommandResult result = parseAndExecuteInput(input);
            assert result != null : "Result from the execution of a command should never be null.";
            exitIfRequired(result);
            addToUndoActions(result);
            useUndoActionIfRequired(result);
            updateFileIfRequired(result);
            return result.getUserMessage();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the welcome message from the Duke application.
     *
     * @return Gets the welcome message for the Duke application.
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
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
