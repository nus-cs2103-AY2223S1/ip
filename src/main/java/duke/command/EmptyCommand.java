package duke.command;

import duke.task.TaskList;

public class EmptyCommand extends Command {
    public EmptyCommand(String[] args) {
        super(CommandType.EMPTY, args);
    }

    @Override
    public void runSpecialTask(TaskList tasks) {
        // does nothing
    }
}
