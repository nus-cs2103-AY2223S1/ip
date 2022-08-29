package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command to delete tasks from the TaskList.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private Storage storage;
    private String[] command;

    /**
     * Creates DeleteCommand with the given TaskList, Storage, and command.
     *
     * @param taskList The TaskList to delete the task from.
     * @param storage The Storage associated with the TaskList.
     * @param command The String array of the user's command.
     */
    public DeleteCommand(TaskList taskList, Storage storage, String[] command) {
        this.taskList = taskList;
        this.storage = storage;
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws DukeException {
        return taskList.deleteTask(command, storage) + "\n";
    }

    /**
     * Checks if the command given refers to deleting tasks.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to delete tasks is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("delete");
    }
}
