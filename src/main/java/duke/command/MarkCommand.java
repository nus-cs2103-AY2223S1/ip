package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     * @param commandOutputs user interface of program.
     * @param storage  files storing task list.
     * @return
     * @throws DukeException if task is already marked or index does not exist.
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage) throws DukeException {
        try {
            if (!taskList.get(index).getDone()) {
                taskList.get(index).markTask();
                new SaveCommand().execute(taskList, commandOutputs, storage);
                return commandOutputs.showMark(taskList, index);
            } else {
                throw new DukeException("Task is already marked");
            }
        } catch (IndexOutOfBoundsException e) {
            //plus 1 for indexing
            throw new DukeException(String.format("Index %d does not exist on the list.", index + 1));
        }
    }
}
