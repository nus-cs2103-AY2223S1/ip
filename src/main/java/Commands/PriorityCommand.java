package Commands;

import Duke.Constants;
import Duke.DukeException;
import Duke.TaskList;
import Duke.Storage;
import Duke.Ui;

import Tasks.Task;

/**
 * Mark command which inherits from Command
 */
public class PriorityCommand extends Command {
    private int idx;
    private String priority;

    public PriorityCommand(int idx, String priority) {
        super();
        this.idx = idx;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (idx <= 0 || idx > t.getSize()) {
            throw new DukeException(Constants.invalidIndex);
        }
        if (!priority.equals("H") && !priority.equals("M") && !priority.equals("L")) {
            throw new DukeException(Constants.invalidPriority);
        }
        Task tsk = t.setTaskPriority(idx, priority);
        storage.save(t.stringfy());
        return ui.printPriority(tsk);
    }

}
