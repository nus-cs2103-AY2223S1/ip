package duke;

public class UnmarkCommand extends Command {
    private int idx;

    /**
     * Constructor for UnmarkCommand
     *
     * @param idx index of task in taskList to be marked as not done
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Unmarks task in taskList and save changes
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String s = taskList.markAsNotDone(idx);
        storage.save(taskList);
        return s;
    }
}
