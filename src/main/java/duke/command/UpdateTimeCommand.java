package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Encapsulates updating the time of a task.
 *
 * @author Kartikeya
 */
public class UpdateTimeCommand implements Command {
    private final String time;
    private final int taskNum;

    /**
     * Constructor for an UpdateTimeCommand object.
     *
     * @param task input string of user
     * @throws DukeException if the input is erroneous
     */
    public UpdateTimeCommand(String task) throws DukeException {
        try {
            String[] taskSplit = task.split(" ", 3);
            this.taskNum = Integer.parseInt(taskSplit[1]);
            this.time = taskSplit[2];
        } catch (NumberFormatException e) {
            throw new DukeException("Please attach a valid task number to the command.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please attach both a valid task number and a "
                    + "non-empty time in this 24-hour format: \"yyyy-mm-dd hhmm\".");
        }
    }

    /**
     * {@inheritDoc}
     * Updates description of task, and shows resulting message to user.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        return itemList.updateTime(taskNum, time);
    }
}
