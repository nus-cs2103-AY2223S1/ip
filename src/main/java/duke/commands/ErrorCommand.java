package duke.commands;

import java.util.Objects;

/**
 * ErrorCommand Class
 */
public class ErrorCommand implements BaseCommand {
    private final String message;

    /**
     * Constructor for the ErrorCommand class
     *
     */
    public ErrorCommand(String message) {
        assert Objects.nonNull(message);
        this.message = message;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(this.message);
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
