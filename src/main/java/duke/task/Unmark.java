package duke.task;

import duke.processor.Storage;
import duke.processor.TaskList;
import duke.Ui;

/**
 * Class to represent "Duke.Task.Unmark" tasks.
 */
public class Unmark extends Task {
    int num;

    /**
     * The constructor for Duke.Task.Mark task
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
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.markUndone(num);
        ui.showUndoneTask(task, num);
        storage.write(task.getTasks());
    }
}
