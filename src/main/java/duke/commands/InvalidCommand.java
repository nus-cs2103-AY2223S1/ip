package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.TaskList;

import java.util.Objects;

/**
 * Representation of all invalid commands
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
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        throw new DukeException(this.errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvalidCommand)) return false;
        InvalidCommand that = (InvalidCommand) o;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage);
    }
}
