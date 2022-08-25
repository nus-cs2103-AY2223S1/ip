package commands;

import java.util.ArrayList;
import tasks.*;

public class MarkCommand extends Command {
    public int index;

    public MarkCommand(int index) {
      this.index = index;
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        taskList.get(index).mark();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + taskList.get(index).toString());
    }
}
