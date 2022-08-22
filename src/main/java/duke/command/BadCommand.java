package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class BadCommand extends Command {
    private static final String BAD_COMMAND = "The command was not understood.";

    public BadCommand(String[] args) {
        super(CommandType.BAD, args);
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        // bypass compatibility check
        this.runSpecialTask(tasks);
    }

    @Override
    public void runSpecialTask(TaskList tasks) throws DukeException {
        throw new DukeException(BAD_COMMAND);
    }
}
