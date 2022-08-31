package duke;

public class MarkCommand extends Command {
    private int idx;

    /**
     * Constructor for MarkCommand
     *
     * @param idx index of task in taskList to be marked as done
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks task in taskList and save changes
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String s = taskList.markAsDone(idx);
        storage.save(taskList);
        return s;
    }
}
