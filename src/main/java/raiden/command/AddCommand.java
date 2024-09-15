package raiden.command;

import raiden.RaidenException;
import raiden.Storage;
import raiden.task.TaskList;

/**
 * Represents the command to add tasks (ToDo, Deadline, and Event) to the TaskList.
 */
public class AddCommand extends Command {
    private TaskList taskList;
    private Storage storage;
    private String[] command;

    /**
     * Creates AddCommand with the given TaskList, Storage, and command.
     *
     * @param taskList The TaskList to add the task to.
     * @param storage The Storage associated with the TaskList.
     * @param command The String array of the user's command.
     */
    public AddCommand(TaskList taskList, Storage storage, String[] command) {
        this.taskList = taskList;
        this.storage = storage;
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws RaidenException {
        return this.taskList.addTask(this.command, this.storage) + "\n";
    }

    /**
     * Checks if the command given refers to adding tasks.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to add tasks is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        boolean isTodo = s.equals("todo");
        boolean isDeadline = s.equals("deadline");
        boolean isEvent = s.equals("event");
        return isTodo || isDeadline || isEvent;
    }
}
