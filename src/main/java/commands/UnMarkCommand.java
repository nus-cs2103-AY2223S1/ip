package commands;

import java.util.ArrayList;
import tasks.*;

public class UnMarkCommand extends Command {
    public int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        taskList.get(index).unmark();
        System.out.println("OK, I've marked this task as not done yet:\n" + "  " + taskList.get(index).toString());
    }
}
