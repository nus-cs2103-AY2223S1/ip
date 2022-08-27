package commands;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public class UnMarkCommand extends Command {
    public int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList taskList) {
        taskList.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:\n" + "  "
                + taskList.retrieveTask(index).toString());
    }
}
