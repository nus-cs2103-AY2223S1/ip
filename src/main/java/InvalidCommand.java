/**
 * This class encapsulates an invalid command from the user.
 */
public class InvalidCommand extends Command {
    private String message;

    InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String execute() {
        return this.message;
    }
}
