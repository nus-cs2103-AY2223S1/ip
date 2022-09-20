package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.NullTaskException;
import uwu.exception.UwuException;
import uwu.task.Task;
import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes a task at the specified index.\n"
            + "(e.g delete 1)";

    public static final String MESSAGE_DETAILED_USAGE = "to delete a task, use the following format:\n"
            + "delete [index of task]\nhave fun~";

    private String userCommand;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public DeleteCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the DeleteCommand which deletes a task of the specified index
     * from the stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task index is out of bounds;
     *                      If command does not have index.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UwuException {
        boolean hasNoIndex = userCommand.toLowerCase().trim().endsWith(COMMAND_WORD);
        if (hasNoIndex) {
            throw new EmptyInputException("oops! the index is missing ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        String indexStr = userCommand.substring(COMMAND_WORD.length()).trim();
        int index = Integer.parseInt(indexStr) - 1;
        boolean isInvalidTask = index >= tasks.size() || index < 0;
        if (isInvalidTask) {
            throw new NullTaskException("hm...it seems that task " + String.valueOf(index + 1) + " does not exist ><"
                    + "\nplease check that you have keyed in the right task number~ <:");
        }

        Task task = tasks.remove(index);
        storage.save(tasks.taskListToStorageString());
        return ui.displayDeletedTask(task, tasks.size());
    }

    /**
     * Returns whether DeleteCommand exits the program.
     *
     * @return false as DeleteCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
