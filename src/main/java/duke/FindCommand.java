package duke;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param keyword String keyword to search for in Task
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find tasks in taskList and lists all matching tasks
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.findTasks(keyword);
    }
}
