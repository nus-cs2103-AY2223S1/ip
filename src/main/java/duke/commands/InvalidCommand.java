package duke.commands;

import java.util.Objects;

import duke.DukeException;
import duke.Message;
import duke.task.TaskList;


/**
 * An invalid command.
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public InvalidCommand(String errorFormat, Object... args) {
        this.errorMessage = String.format(errorFormat, args);
    }

    @Override
    public Message execute(TaskList tasks) throws DukeException {
        throw new DukeException(this.errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvalidCommand)) {
            return false;
        }
        InvalidCommand that = (InvalidCommand) o;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage);
    }
}
