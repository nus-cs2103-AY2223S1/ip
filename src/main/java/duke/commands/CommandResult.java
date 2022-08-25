package duke.commands;

/**
 * Represents the result of a command's execution.
 */
public class CommandResult {
    private final String userMessage;
    private final boolean hasUpdates;
    private final boolean isExit;

    /**
     * Constructor for a command result that takes in userMessage, hasUpdates and isExit.
     *
     * @param userMessage User message to be displayed to the user.
     * @param hasUpdates  Whether the command resulted in any changes to the task list.
     * @param isExit      Whether the command requests for the termination of the application.
     */
    public CommandResult(String userMessage, boolean hasUpdates, boolean isExit) {
        this.userMessage = userMessage;
        this.hasUpdates = hasUpdates;
        this.isExit = isExit;
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
     *
     * @return Whether there are any updates to the file.
     */
    public boolean shouldUpdateFile() {
        return hasUpdates;
    }

    /**
     * Gets whether the command requests for the termination of the application.
     *
     * @return Whether the application should exit.
     */
    public boolean shouldExit() {
        return isExit;
    }
}
