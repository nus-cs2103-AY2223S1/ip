package duke;

/**
 * UnmarkCommand is a Command that unmarks a Task in the TaskList.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructor for UnmarkCommand
     *
     * @param input input from user.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes unmark command and returns output to user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            //  error checking
            if (input.length() == 6 || input.substring(7).equals("")) {
                throw new IllegalIndexException("OOPS!!! The index of an unmark cannot be empty.");
            }
            //  Isolates the index after "unmark"
            String remainder = input.substring(7);
            int index = Integer.valueOf(remainder) - 1;
            taskList.unmark(index);
            storage.updateStorage(taskList);
            return ui.unmarkResponse(taskList, index);
        } catch (IllegalIndexException e) {
            return e.getMessage();
        }
    }
}
