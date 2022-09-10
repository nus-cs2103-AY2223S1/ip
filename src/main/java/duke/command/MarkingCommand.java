package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a command to mark a certain task as done or undone.
 */
public class MarkingCommand extends Command {
    private String fullCommand;
    /**
     * Class constructor.Construct a command for marking.
     * @param fullCommand A string of a line from System.in. Begin with "mark" or "unmark".
     */
    public MarkingCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Execute the mark command. Mark the index task as done or undone.
     * @param taskList The taskList containing the target task.
     * @param storage The object containing the corresponding file.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String typeDescription = this.fullCommand.split(" ")[0];
        if (typeDescription.equals("mark")) {
            int index = Integer.valueOf(this.fullCommand.split(" ")[1]) - 1;
            return taskList.markAsDone(index, storage);
        } else {
            int index = Integer.valueOf(this.fullCommand.split(" ")[1]) - 1;
            return taskList.markUndone(index, storage);
        }
    }
}
