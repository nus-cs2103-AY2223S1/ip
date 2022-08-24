package duke.task;

import duke.processor.Storage;
import duke.processor.TaskList;
import duke.Ui;

/**
 * Class to represent "Duke.Task.Mark" tasks.
 */
public class Mark extends Task {
    int num;

    /**
     * The constructor for Duke.Task.Mark task
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
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.markDone(num);
        ui.showDoneTask(task, num);
        storage.write(task.getTasks());
    }
}
