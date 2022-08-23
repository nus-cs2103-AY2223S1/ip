package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TasksList;

/**
 * Represents the command to mark a task as done in the TasksList.
 */
public class MarkCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private String[] command;

    /**
     * Creates MarkCommand with the given TasksList, Storage and command.
     *
     * @param tasksList The TasksList to mark the task from.
     * @param storage The Storage associated with the TasksList.
     * @param command The String array of the user's command.
     */
    public MarkCommand(TasksList tasksList, Storage storage, String[] command) {
        this.tasksList = tasksList;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public void execute() throws DukeException {
        this.tasksList.markTask(command, storage);
    }

    /**
     * Checks if the command given refers to marking a task.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to mark a task is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("mark");
    }
}
