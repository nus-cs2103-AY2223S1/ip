package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TasksList;

/**
 * Represents the command to delete tasks from the TasksList.
 */
public class DeleteCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private String[] command;

    /**
     * Creates DeleteCommand with the given TasksList, Storage, and command.
     *
     * @param tasksList The TasksList to delete the task from.
     * @param storage The Storage associated with the TasksList.
     * @param command The String array of the user's command.
     */
    public DeleteCommand(TasksList tasksList, Storage storage, String[] command) {
        this.tasksList = tasksList;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public void execute() throws DukeException {
        tasksList.deleteTask(command, storage);
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
