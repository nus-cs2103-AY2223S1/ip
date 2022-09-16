package duke;

/**
 * This class handles all commands related to showing an individual task
 * and inherits from the Command class.
 */
public class ShowCommand extends Command {
    private int index;
    /**
     * Constructor for the ShowCommand class.
     */
    public ShowCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Lists all the tasks in the tasklist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayTask("Here:", taskList.getTask(index));
    }

}
