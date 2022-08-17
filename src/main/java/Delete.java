/**
 * Class to represent "Delete" tasks.
 */
public class Delete extends Task {
    int num;

    /**
     * The constructor for Delete task
     */
    public Delete(int num) {
        super("delete", false);
        this.num = num;
    }

    /**
     * Executes input list task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui) {
        Task taskDeleted = task.delete(num);
        ui.showDeleteTask(task, taskDeleted);
    }
}
