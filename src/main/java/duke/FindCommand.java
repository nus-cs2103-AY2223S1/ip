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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.findTasks(keyword);
    }
}
