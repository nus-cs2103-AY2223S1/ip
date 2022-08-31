package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to unmark a task in task list.
 */
public class UnMarkCommand extends Command {

    private final int index;

    /**
     * Constructor for UnMarkCommand class.
     *
     * @param index index of task in task list.
     */
    public UnMarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Unmarks task in task list and saves it.
     *
     * @param taskList task list.
     * @param commandOutputs       user interface of program.
     * @param storage  files storing task list.
     * @return
     * @throws DukeException if task is already unmarked or index does not exist.
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage) throws DukeException {
        try {
            if (taskList.get(index).getDone()) {
                taskList.get(index).unmarkTask();
                new SaveCommand().execute(taskList, commandOutputs, storage);
                return commandOutputs.showUnmark(taskList, index);
            } else {
                throw new DukeException("Task is already unmarked");
            }
        } catch (IndexOutOfBoundsException e) {
            //plus 1 for indexing
            throw new DukeException(String.format("Index %d does not exist on the list.", index + 1));
        }
    }

}
