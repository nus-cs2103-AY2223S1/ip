package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to save task list in save file.
 */
public class SaveCommand extends Command {

    /**
     * Constructor for SaveCommand class.
     */
    public SaveCommand() {
        super(false);
    }

    /**
     * Saves tasks in task list to save file.
     *
     * @param taskList task list.
     * @param commandOutputs       user interface of program.
     * @param storage  files storing task list.
     * @return
     */
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage) {
        storage.saveTaskList(taskList);
        return null;
    }
}
