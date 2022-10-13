package meowmeow.commands;
import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class AddCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used to add a task to the task list.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class AddCommand extends Command {

    private char taskType;
    private String userInput;

    /**
     * Constructor for AddCommand.
     * @param taskType the type of task to be added.
     *                 'T' for todo, 'D' for deadline, 'E' for event.
     * @param userInput the user input to be added.
     *                  The user input can contain the name of the task and the date and time of the task.
     */
    public AddCommand(char taskType, String userInput) {
        this.taskType = taskType;
        this.userInput = userInput;
    }

    /**
     * Executes the AddCommand.
     *
     * @param tasks the task list to be operated on by the command.
     *              The task list is used to add the task to the task list.
     * @param ui the user interface to be used by the command.
     *           The user interface is used to display the result of the command.
     * @param storage the storage to be used by the command.
     *                The storage is used to save and load the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskType == 'T') {
            return tasks.addTodo(userInput);
        } else if (taskType == 'D') {
            return tasks.addDeadline(userInput);
        } else if (taskType == 'E') {
            return tasks.addEvent(userInput);
        }
        return "Something went wrong with AddCommand";
    }

    /**
     * Returns false for AddCommand.
     * @return false for AddCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
