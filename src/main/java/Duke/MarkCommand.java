package duke;

/**
 * MarkCommand is a Command that marks a Task in the TaskList.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Constructor for MarkCommand
     *
     * @param input input from user.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes mark command and returns output to user.
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
            if (input.length() == 4 || input.substring(5).equals("")) {
                throw new IllegalIndexException("OOPS!!! The index of a mark cannot be empty.");
            }
            //  Isolates the index after "mark"
            String remainder = input.substring(5);
            int index = Integer.valueOf(remainder) - 1;
            taskList.mark(index);
            storage.updateStorage(taskList);
            return ui.markResponse(taskList, index);
        } catch (IllegalIndexException e) {
            return e.getMessage();
        }
    }
}
