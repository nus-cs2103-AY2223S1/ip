package duke;

/**
 * This class handles all commands related to marking tasks
 * and inherits from the Command class.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for the MarkCommand class.
     *
     * @param index to indicate the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks a task from the tasklist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(index);
        Task temp = taskList.getTask(index);

        boolean status = storage.saveData(taskList);
        String returnString = ui.displayTask(ui.MARKED, temp);
        return status ? returnString : returnString + "Error saving";
    }

}
