package duke.command;

import duke.task.Deadline;

public class DeadlineCommand extends TaskCommand {

    public static final String COMMAND_NAME = "deadline";

    public DeadlineCommand(Deadline newDeadline) {
        super(newDeadline);
    }
}