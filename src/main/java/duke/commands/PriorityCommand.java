package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * The PriorityCommand class represents user command priority.
 */
public class PriorityCommand extends Command {

    public static final String COMMAND_WORD = "priority";
    private int index;
    private String priority;

    /**
     * Constructor for MarkCommand that takes in
     * an integer representing Task to mark done.
     * @param i Specified index.
     */
    public PriorityCommand(int i, String priority) {
        this.index = i;
        this.priority = priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.attachPriority(index, priority);
        storage.save(tasks.getTaskList());
        ui.showPriority(t, priority);
    }

}
