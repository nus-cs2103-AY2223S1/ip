package command;

import duke.TaskList;

public class ListCommand extends Command{
    private TaskList taskList;
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        return taskList.list();
    }
}
