package duke;

/**
 * DeleteCommand is a Command that deletes a Task in the TaskList.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor for DeleteCommand.
     *
     * @param input input from user.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the DeleteCommand and returns output to user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            //  Isolates index after "delete"
            String remainder = input.substring(7);
            int index = Integer.valueOf(remainder) - 1;

            // error checking
            if (index < 0 || index >= taskList.size()) {
                throw new IllegalIndexException("Index invalid!");
            }
            String response = ui.deleteResponse(taskList, index);
            taskList.deleteTask(index);
            storage.updateStorage(taskList);
            return response;
        } catch (IllegalIndexException e) {
            return e.getMessage();
        }
    }
}
