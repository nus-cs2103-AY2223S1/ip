package duke;

import java.util.Objects;

/**
 * Represents a Command to mark a task as done or not done in Duke.
 */
public class MarkCommand extends Command {
    protected int index;
    protected boolean isDone;

    /**
     * Constructor of MarkCommand with index of Task to mark and boolean to mark the Task as done or not done.
     *
     * @param index Index of Task to mark.
     * @param isDone Boolean to mark the Task as done or not done.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Run the MarkCommand, mark a task as done or not done in Duke.
     *
     * @param duke Duke instance to run the MarkCommand at.
     * @throws DukeException If mark is unsuccessful.
     */
    @Override
    public void run(Duke duke) throws DukeException {
        duke.markTask(index, isDone);
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
        MarkCommand that = (MarkCommand) o;
        return index == that.index && isDone == that.isDone;
    }
}
