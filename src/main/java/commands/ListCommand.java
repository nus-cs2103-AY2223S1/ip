package commands;

import java.util.ArrayList;
import tasks.*;

public class ListCommand extends Command {

    public ListCommand() {
      super();
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks at the moment!");
        }
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
    }
}
