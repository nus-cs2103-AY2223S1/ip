package commands;

import tasks.*;

public class UnMarkCommand extends Command {
    public int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList taskList) {
        taskList.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:\n" + "  " + taskList.retrieveTask(index).toString());
    }
}
