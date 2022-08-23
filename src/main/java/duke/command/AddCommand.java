package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TasksList;

/**
 * Represents the command to add tasks (ToDo, Deadline, and Event) to the TasksList.
 */
public class AddCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private String[] command;

    /**
     * Creates AddCommand with the given TasksList, Storage, and command.
     *
     * @param tasksList The TasksList to add the task to.
     * @param storage The Storage associated with the TasksList.
     * @param command The String array of the user's command.
     */
    public AddCommand(TasksList tasksList, Storage storage, String[] command) {
        this.tasksList = tasksList;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public void execute() throws DukeException {
        tasksList.addTask(this.command, this.storage);
    }

    /**
     * Checks if the command given refers to adding tasks.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to add tasks is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("todo") || s.equals("deadline") || s.equals("event");
    }
}
