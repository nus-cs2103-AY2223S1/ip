package duke.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
