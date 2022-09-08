package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.Task;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * AddCommand class to represent command to add a new task in the tasklist.
 *
 * @author liviamil
 */

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
//            System.out.println("enters execute sally.command.AddCommand, task: " + task);
            tasks.addTask(task);
//            String printTask = "";
//            for (int i = 0; i < tasks.getNumOfTasks(); i++) {
//                printTask = printTask + tasks.getTask(i) + "\n";
//            }
//            System.out.println("added tasks (in execute), tasks: " + printTask);
            String addedTask = task.toString();
            int totalTasks = tasks.getNumOfTasks();
            storage.savesFile(tasks);
            ui.showAddedTask(addedTask, totalTasks);
        } catch (SallyException e) {
            System.out.println("Oops! File Not Found.");
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
