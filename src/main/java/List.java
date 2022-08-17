/**
 * Class to represent "List" tasks.
 */
public class List extends Task {
    /**
     * The constructor for List task
     */
    public List() {
        super("list", false);
    }

    /**
     * Executes input list task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui) {
        ui.showListDetails(task);
    }
}
