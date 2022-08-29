package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command to mark a task as done in the TaskList.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private Storage storage;
    private String[] command;

    /**
     * Creates MarkCommand with the given TaskList, Storage and command.
     *
     * @param taskList The TaskList to mark the task from.
     * @param storage The Storage associated with the TaskList.
     * @param command The String array of the user's command.
     */
    public MarkCommand(TaskList taskList, Storage storage, String[] command) {
        this.taskList = taskList;
        this.storage = storage;
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws DukeException {
        return this.taskList.markTask(command, storage) + "\n";
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
