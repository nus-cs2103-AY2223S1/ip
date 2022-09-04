package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * HighCommand is a Command that sets a Task to high priority.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class HighCommand extends Command {
    private int idx;

    /**
     * Constructor for HighCommand.
     *
     * @param idx Index of Task to be set to high priority.
     */
    public HighCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns the response from Duke after setting the Task with the given index to high priority.
     *
     * @param tasks tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.getSize()) {
            throw new DukeException("The index provided is not within the list.");
        }

        String task = tasks.setHighPriorityTask(idx);
        storage.save(tasks.saveTasks());

        return ui.showHighPriority(task);
    }
}
