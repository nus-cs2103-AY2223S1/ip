package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand is a Command that marks a Task as completed.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class MarkCommand extends Command {
    private int idx;

    /**
     * Constructor for MarkCommand.
     *
     * @param idx Index of Task to be marked as completed.
     */
    public MarkCommand(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Marks the Task with the given index as completed.
     *
     * @param tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.getSize()) {
            throw new DukeException("The index provided is not within the list.");
        }
        ui.showMark(tasks.markTask(idx));
        storage.save(tasks.saveTasks());
    }
}
