package raiden.command;

import raiden.RaidenException;
import raiden.Storage;
import raiden.task.TaskList;

/**
 * Represents the command to add tasks (ToDo, Deadline, and Event) to the TaskList.
 */
public class EditCommand extends Command {
    private TaskList taskList;
    private Storage storage;
    private String[] command;

    /**
     * Creates EditCommand with the given TaskList, Storage, and command.
     *
     * @param taskList The TaskList to add the task to.
     * @param storage The Storage associated with the TaskList.
     * @param command The String array of the user's command.
     */
    public EditCommand(TaskList taskList, Storage storage, String[] command) {
        this.taskList = taskList;
        this.storage = storage;
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws RaidenException {
        return this.taskList.editTask(this.command, this.storage) + "\n";
    }

    /**
     * Checks if the command given refers to editing tasks.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to edit tasks is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("editT") || s.equals("editD");
    }
}
