/**
 * Class representing the command for KKBot when user inputs a todo task
 *
 * @author AkkFiros
 */

public class ToDoCommand extends Command {
    public static final String KEYWORD = "todo";
    private String description;

    /**
     * Constructor for a ToDoCommand
     * @param description the description of the user-input task
     */
    public ToDoCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Returns a set of actions done by KKBot when user inputs "todo".
     * @param tasks the list of tasks stored by KKBot
     * @param display the display object that governs what response is returned to the user
     * @return the related messages after a todo task is added to KKBot
     */
    @Override
    public String execute(TaskList tasks, Display display) {
        ToDo task = new ToDo(description);
        tasks.addTask(task);
        return display.showTaskAddition(task)
                + display.showNumberOfTasks(tasks.getNumberOfTasks());
    }
}
