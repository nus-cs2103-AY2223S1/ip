package duke;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand
     *
     * @param task task to be added to taskList
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to taskList and save changes
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String s = taskList.addTask(task);
        storage.save(taskList);
        return s;
    }
}
