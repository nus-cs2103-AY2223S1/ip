/**
 * Class to represent "Unmark" tasks.
 */
public class Unmark extends Task {
    int num;

    /**
     * The constructor for Mark task
     */
    public Unmark(int num) {
        super("unmark", false);
        this.num = num;
    }

    /**
     * Executes input list task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui) {
        task.markUndone(num);
        ui.showUndoneTask(task, num);
    }
}
