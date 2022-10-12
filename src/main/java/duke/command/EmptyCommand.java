package duke.command;

import duke.task.TaskList;

public class EmptyCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return null;
    }
}
