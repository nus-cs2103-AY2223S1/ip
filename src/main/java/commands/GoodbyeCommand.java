package commands;

import tasklist.TaskList;

public class GoodbyeCommand extends Command {
    public GoodbyeCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList) {
        this.exit = true;
    }
}
