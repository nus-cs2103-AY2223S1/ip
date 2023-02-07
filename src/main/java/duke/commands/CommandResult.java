package duke.commands;

import java.util.Objects;

/**
 * CommandResult Class
 */
public class CommandResult {
    private final String message;

    /**
     * Constructor for CommandResult Class
     *
     */
    public CommandResult(String message) {
        assert Objects.nonNull(message);
        this.message = message;
    }

    /**
     * The getMessage function returns the message that was passed into the
     * constructor.
     *
     * @return The message variable
     */
    public String getMessage() {
        return message;
    }
}
