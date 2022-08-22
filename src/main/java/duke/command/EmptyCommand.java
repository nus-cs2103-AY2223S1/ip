package duke.command;

import duke.task.TaskList;

public class EmptyCommand extends Command {
    public EmptyCommand() {
        super(CommandType.EMPTY, new String[]{});
    }

    @Override
    public void execute(TaskList tasks) {
        // does nothing
    }
}
