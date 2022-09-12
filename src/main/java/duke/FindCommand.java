package duke;

/**
 * Encapsulate a command that allow users to find existing task with
 * matching description, which is-a Command.
 */
public class FindCommand extends Command {

    private String taskDescription;

    /**
     * Constructs an instance of FindCommand which inherits from Command.
     *
     * @param taskDescription description of task that user wants to find.
     */
    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(taskDescription);
        return ui.showFilteredList(matchingTasks);
    }
}
