package duke;

public class ListCommand extends Command {
    /**
     * Lists all tasks in taskList
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.showTasks();
    }
}
