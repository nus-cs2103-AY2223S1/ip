package duke;

/**
 * FindCommand is a Command that finds tasks in the TaskList with a matching keyword.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructor for FindCommand.
     *
     * @param input input from user.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the FindCommand and returns list of tasks to user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //  Isolates string input after "find"
        String keyword = input.substring(5);
        return ui.find(taskList, keyword);
    }
}
