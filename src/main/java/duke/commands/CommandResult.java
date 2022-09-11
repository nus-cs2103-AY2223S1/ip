package duke.commands;

import duke.undo.UndoAction;

/**
 * Represents the result of a command's execution.
 */
public class CommandResult {
    /**
     * Action to be performed based on the command.
     */
    protected enum Action {
        NONE,
        UPDATE_FILE,
        EXIT,
        UNDO,
    }

    private final String userMessage;
    private final Action action;
    private final UndoAction undoAction;

    /**
     * Constructs a command result.
     *
     * @param userMessage User message to be displayed to the user.
     * @param action      Action to be performed.
     */
    public CommandResult(String userMessage, Action action) {
        this.userMessage = userMessage;
        this.action = action;
        this.undoAction = null;
    }

    /**
     * Constructs a command result.
     *
     * @param userMessage User message to be displayed to the user.
     * @param undoAction  Undo action to be performed.
     */
    public CommandResult(String userMessage, Action action, UndoAction undoAction) {
        this.userMessage = userMessage;
        this.action = action;
        this.undoAction = undoAction;
    }

    /**
     * Constructs a command result.
     *
     * @param userMessage User message to be displayed to the user.
     */
    public CommandResult(String userMessage) {
        this.userMessage = userMessage;
        this.action = Action.NONE;
        this.undoAction = null;
    }

    /**
     * Gets the user message to be displayed to the user.
     *
     * @return User message.
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * Gets whether the command resulted in any changes to the task list.
     * Undo action will require an update to the file.
     *
     * @return Whether there are any updates to the file.
     */
    public boolean shouldUpdateFile() {
        return action == Action.UPDATE_FILE || action == Action.UNDO;
    }

    /**
     * Gets whether the command requests for the termination of the application.
     *
     * @return Whether the application should exit.
     */
    public boolean shouldExit() {
        return action == Action.EXIT;
    }

    /**
     * Gets the undo action.
     * Null if there is no undo action.
     *
     * @return Undo action or null.
     */
    public UndoAction getUndoAction() {
        return undoAction;
    }

    /**
     * Gets whether the command requests for the undoing the previous command.
     *
     * @return Whether the application should undo the previous command.
     */
    public boolean shouldUndo() {
        return action == Action.UNDO;
    }
}
