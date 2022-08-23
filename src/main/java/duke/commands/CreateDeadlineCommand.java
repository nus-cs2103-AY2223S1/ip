package duke.commands;

import duke.task.Deadline;

public class CreateDeadlineCommand extends CreateTaskCommand {
    public CreateDeadlineCommand(Deadline deadline) {
        super(deadline);
    }
}