package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a command to delete task in the taskList.
 */
public class DeleteCommand extends Command {
    private String fullCommand;
    /**
     * Class constructor. Construct a deleteCommand object.
     * @param fullCommand A string of a line from the System.in. With the format as "delete x"
     *                    where x is an integer.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Execute the delete command. Remove the target task from taskList.
     * Throws DukeException when error occurs.
     * @param taskList The target taskList that will be added or deleted task.
     * @param storage The object containing the corresponding file.
     * @throws DukeException Throws DukeException when the task index is out of index.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.delete(Integer.valueOf(this.fullCommand.split(" ")[1]),
                storage);
    }
}
