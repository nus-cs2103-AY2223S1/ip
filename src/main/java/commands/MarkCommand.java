package commands;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public class MarkCommand extends Command {
    public int index;

    public MarkCommand(int index) {
      this.index = index;
    }

    @Override
    public void run(TaskList taskList) {
        taskList.mark(index);
        System.out.println("Nice! I've marked this task as done:\n" + "  " + taskList.retrieveTask(index).toString());
    }
}
