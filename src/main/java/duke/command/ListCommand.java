package duke.command;

import duke.logic.TaskList;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        super();
        this.taskList = taskList;
    }

    @Override
    public void run() {
        System.out.println("Here are the tasks in your list:");
        System.out.print(this.taskList.toString());
    }
}
