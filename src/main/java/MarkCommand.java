/**
 * Class representing the command when user inputs mark command.
 *
 * @author AkkFiros
 */

public class MarkCommand extends Command {
    public static final String KEYWORD = "mark";
    private int index; // index number of the task in taskList

    /**
     * Constructor for MarkCommand
     * @param inputNumber the number input by user for
     *                    the task they want to mark as done
     */
    public MarkCommand(int inputNumber) {
        super();
        index = inputNumber - 1;
    }

    /**
     * Returns a command for KKBot when user inputs "mark".
     * @param tasks the list of tasks stored by KKBot
     * @param display the display object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the message shown when a task is marked as complete
     * @throws InvalidTaskException when the user input is wrong
     * @throws StorageException if there is an error reading from/writing to hard drive
     */
    public String execute(TaskList tasks, Display display, Storage storage)
            throws InvalidTaskException, StorageException {
        Task task = tasks.changeTaskStatus(index, true);
        storage.save(tasks);
        return display.showTaskDone(task);
    }
}
