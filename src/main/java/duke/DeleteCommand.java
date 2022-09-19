package duke;

import java.util.Objects;

/**
 * Represents a Command to delete a Task from Duke.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Constructor of DeleteCommand with index of Task to delete.
     *
     * @param index Index of Task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Run the DeleteCommand, delete a Task from Duke.
     *
     * @param duke Duke instance to run the Command at.
     * @throws DukeException If delete is unsuccessful.
     */
    @Override
    public void run(Duke duke) throws DukeException {
        duke.deleteTask(index);
    }

    /**
     * Checks equality to another Object.
     *
     * @param o Other Object.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return index == that.index;
    }
}
