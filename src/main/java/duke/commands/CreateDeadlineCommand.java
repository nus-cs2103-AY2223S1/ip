package duke.commands;

import duke.task.Deadline;

/**
 * Creates and stores a deadline.
 */
public class CreateDeadlineCommand extends CreateTaskCommand {
    /**
     * Constructor for CreateDeadlineCommand.
     * @param deadline the deadline to be stored
     */
    public CreateDeadlineCommand(Deadline deadline) {
        super(deadline);
    }
}
