package duke;

/**
 * Encapsulate a command that allow users to find existing task with
 * matching description, which is-a Command.
 */
public class FindCommand extends Command {

    private String taskDescription;

    /**
     * Class constructor of FindCommand.
     *
     * @param taskDescription description of task that user wants to find.
     */
    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Find existing task with description that contains the user input.
     *
     * @param tasks list of existing tasks.
     * @param ui user interface to be shown.
     * @param storage to rewrite the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(taskDescription);
        ui.showFilteredList(matchingTasks);
    }
}
