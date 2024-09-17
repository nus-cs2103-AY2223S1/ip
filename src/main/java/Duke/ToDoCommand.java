package duke;

/**
 * ToDoCommand is a Command that adds a Todo task to the TaskList.
 */
public class ToDoCommand extends Command {
    private String input;

    /**
     * Constructor for ToDoCommand.
     *
     * @param input input from user.
     */
    public ToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the ToDoCommand and returns the output to user.
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
            if (input.length() == 4 || input.substring(5).equals("")) {
                throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
            }
            //  Isolates the task description after "todo"
            String description = input.substring(5);

            ToDo todo = new ToDo(description);
            taskList.addTask(todo);
            storage.updateStorage(taskList);
            return ui.addResponse(todo, taskList);
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }
}
