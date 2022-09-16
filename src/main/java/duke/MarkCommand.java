package duke;

/**
 * Represents a Command to mark a task as done or not done in Duke.
 */
public class MarkCommand extends Command {
    int index;
    boolean isDone;

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
     */
    @Override
    public void run(Duke duke) {
        duke.markTask(index, isDone);
    }
}
