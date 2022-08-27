package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark a task in task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for MarkCommand class.
     *
     * @param index index of task in task list.
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Marks task in task list and saves it.
     *
     * @param taskList task list.
     * @param ui user interface of program.
     * @param storage files storing task list.
     * @throws DukeException if task is already marked or index does not exist.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (!taskList.get(index).getDone()) {
                taskList.get(index).markTask();
                ui.showMark(taskList, index);
            } else {
                throw new DukeException("Task is already marked");
            }
        } catch (IndexOutOfBoundsException e) {
            //plus 1 for indexing
            throw new DukeException(String.format("Index %d does not exist on the list.", index + 1));
        }
        new SaveCommand().execute(taskList, ui, storage);
    }
}
