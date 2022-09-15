package anya;

/**
 * Represents a command to add the task in the current tasklist.
 */
public class AddCommand extends Command {

    private final Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the add command.
     * @param tasks current tasklist.
     * @param ui interaction class.
     * @return response of Anya.
     */
    String execute(TaskList tasks, Ui ui) {
        int noOfTasks = tasks.add(this.task);
        String task = this.task.toString();
        String response = ui.added(task, noOfTasks);
        return response;
    }

}
