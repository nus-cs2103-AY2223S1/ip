package duke;

/**
 * This class handles all commands related to unmarking tasks
 * and inherits from the Command class.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param index to indicate the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Unmarks a task from the tasklist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.unmarkTask(index);
        Task temp = taskList.getTask(index);
        storage.save(taskList, ui);
        return ui.displayTask(ui.UNMARKED, temp);
    }

    /**
     * Returns false.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
