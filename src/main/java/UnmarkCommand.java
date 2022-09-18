/**
 * Class representing the command when user inputs unmark command.
 *
 * @author AkkFiros
 */

public class UnmarkCommand extends Command {
    public static final String KEYWORD = "unmark";
    private int index; // index number of the task in taskList

    /**
     * Constructor for MarkCommand
     * @param inputNumber the number input by user for
     *                    the task they want to mark as done
     */
    public UnmarkCommand(int inputNumber) {
        super();
        index = inputNumber - 1;
    }

    /**
     * Returns a command for KKBot when user inputs "unmark".
     * @param tasks the list of tasks stored by KKBot
     * @param display the display object that governs what response is returned to the user
     * @return the message shown when a task is marked as incomplete
     * @throws InvalidTaskException when the user input is wrong
     */
    public String execute(TaskList tasks, Display display) throws InvalidTaskException {
        Task task = tasks.changeTaskStatus(index, false);
        return display.showTaskDone(task);
    }
}
