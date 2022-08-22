package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String[] args) {
        super(CommandType.LIST, args);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.print();
    }
}

