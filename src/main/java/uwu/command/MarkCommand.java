package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.NullTaskException;
import uwu.exception.UwuException;
import uwu.task.Task;
import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Marks a task in the task list.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": marks a task at the specified index as complete.\n"
            + "(e.g mark 1)";

    public static final String MESSAGE_DETAILED_USAGE = "to mark a task as complete, use the following format:\n"
            + "mark [index of task]\nhave fun~";

    private String userCommand;

    /**
     * Constructs a MarkCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public MarkCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the MarkCommand which marks the task at the
     * specified index.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task index is out of bounds;
     *                      If task has already been marked;
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
        boolean hasBeenMarked = tasks.get(index).getIsDone();

        if (isInvalidTask) {
            throw new NullTaskException("hm...it seems that task " + String.valueOf(index + 1) + " does not exist ><"
                    + "\nplease check that you have keyed in the right task index~ <:");
        }

        if (hasBeenMarked) {
            throw new NullTaskException("hm...it seems that task " + String.valueOf(index + 1) + "has been marked ><"
                    + "\nplease try marking another task~ <:");
        }

        Task markedTask = tasks.get(index);
        markedTask.setIsDone(true);
        storage.save(tasks.taskListToStorageString());
        return ui.displayMarkedTask(markedTask);
    }

    /**
     * Returns whether MarkCommand exits the program.
     *
     * @return false as MarkCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
