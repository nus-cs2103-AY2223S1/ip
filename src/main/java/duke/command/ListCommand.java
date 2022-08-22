package duke.command;

import duke.task.TasksList;

public class ListCommand extends Command {
    private TasksList tasksList;

    public ListCommand(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    @Override
    public void execute() {
        this.tasksList.listTasks();
    }

    public static boolean isCommand(String s) {
        return s.equals("list");
    }
}
