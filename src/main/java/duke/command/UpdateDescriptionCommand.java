package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Encapsulates updating the description of a task.
 *
 * @author Kartikeya
 */
public class UpdateDescriptionCommand implements Command {
    private final String description;
    private final int taskNum;

    /**
     * Constructor for an UpdateDescriptionCommand object.
     *
     * @param task input string of user
     * @throws DukeException if the input is erroneous
     */
    public UpdateDescriptionCommand(String task) throws DukeException {
        try {
            String[] taskSplit = task.split(" ", 3);
            this.taskNum = Integer.parseInt(taskSplit[1]);
            this.description = taskSplit[2];
        } catch (NumberFormatException e) {
            throw new DukeException("Please attach a valid task number to the command.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please attach a non-empty description.");
        }
    }

    /**
     * {@inheritDoc}
     * Updates description of task, and shows resulting message to user.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        return itemList.updateDescription(taskNum, description);
    }
}
