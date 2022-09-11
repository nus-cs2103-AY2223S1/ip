package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.NullTaskException;
import uwu.exception.UwuException;
import uwu.task.Task;
import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Unmarks a task in the task list.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": unmarks a task at the specified index as incomplete.\n"
            + "(e.g unmark 1)";

    public static final String MESSAGE_DETAILED_USAGE = "to mark a task as incomplete, use the following format:\n"
            + "unmark [index of task]\nhave fun~";

    private String userCommand;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public UnmarkCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the UnmarkCommand which unmarks the task at the
     * specified index.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task index is out of bounds;
     *                      If task has already been unmarked;
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
        boolean hasBeenUnmarked = !tasks.get(index).getIsDone();
        if (isInvalidTask) {
            throw new NullTaskException("hm...it seems that task " + String.valueOf(index + 1) + " does not exist ><"
                    + "\nplease check that you have keyed in the right task index~ <:");
        }

        if (hasBeenUnmarked) {
            throw new NullTaskException("hm...it seems that task " + String.valueOf(index + 1) + "has been unmarked ><"
                    + "\nplease try unmarking another task~ <:");
        }

        Task unmarkedTask = tasks.get(index);
        unmarkedTask.setIsDone(false);
        storage.save(tasks.taskListToStorageString());
        return ui.displayUnmarkedTask(unmarkedTask);
    }

    /**
     * Returns whether UnmarkCommand exits the program.
     *
     * @return false as UnmarkCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
