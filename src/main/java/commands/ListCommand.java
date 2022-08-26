package commands;

import tasks.*;

/**
 * ListCommand prints out all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
      super();
    }

    @Override
    /**
     * Executes ListCommand by iterating through each task and printing out its string representation.
     *
     * @param taskList Task list to be printed out.
     */
    public void run(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("You have no tasks at the moment!");
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + ". " + taskList.retrieveTask(i - 1).toString());
        }
    }
}
