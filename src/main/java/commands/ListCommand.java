package commands;

import tasks.*;

public class ListCommand extends Command {

    public ListCommand() {
      super();
    }

    @Override
    public void run(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("You have no tasks at the moment!");
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + ". " + taskList.retrieveTask(i - 1).toString());
        }
    }
}
