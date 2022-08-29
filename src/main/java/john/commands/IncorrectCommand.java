package john.commands;

/**
 * Represents an incorrect command.
 */
public class IncorrectCommand extends Command {
    private final String feedback;

    /**
     * Constructor for an incorrect command without a feedback.
     */
    public IncorrectCommand() {
        this.feedback = "";
    }

    /**
     * Constructor for an IncorrectCommand.
     * @param feedback The error feedback to display to users.
     */
    public IncorrectCommand(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Returns the feedback of an incorrect command.
     * @return A string representation of the incorrect command.
     */
    @Override
    public String execute() {
        return feedback.equals("") ? "|  cannot understand command\n" : feedback;
    }
}
