package duke;

/**
 * DeadlineCommand is a Command that adds a Deadline task to the TaskList.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param input input from user.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the DeadlineCommand and returns output to user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            //  Error checking
            if (input.length() == 8 || input.substring(9).equals("")) {
                throw new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
            }
            //  Isolates remaining input after "deadline" and filters further for task description and time
            String remainder = input.substring(9);
            String[] arr = remainder.split("/by");
            String description = arr[0].trim();
            String deadline = arr[1].trim();

            Deadline deadlineTask = new Deadline(description, deadline);
            taskList.addTask(deadlineTask);
            storage.updateStorage(taskList);
            return ui.addResponse(deadlineTask, taskList);
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }
}
