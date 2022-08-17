/**
 * Class to represent "Mark" tasks.
 */
public class Mark extends Task {
    int num;

    /**
     * The constructor for Mark task
     */
    public Mark(int num) {
        super("mark", false);
        this.num = num;
    }

    /**
     * Executes input list task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui) {
        task.markDone(num);
        ui.showDoneTask(task, num);
    }
}
