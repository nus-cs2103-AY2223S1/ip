package Commands;

import TaskList.TaskList;
import common.Ui;

public class GoodbyeCommand extends Command {
    public GoodbyeCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList) {
        this.exit = true;
    }
}
