package duke.command;

import duke.task.TasksList;

public class ListCommand extends Command {
    private TasksList tasksList;

    public ListCommand(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    @Override
    public String execute() {
        return this.tasksList.toString();
    }
}
