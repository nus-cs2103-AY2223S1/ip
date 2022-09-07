package duke;

/**
 * Represents a command to find matching task in the current tasklist that contains the keyword.
 */
public class FindCommand extends Command {

    private final String keyword;
    private static boolean isExit;

    FindCommand(String keyword) {
        this.keyword = keyword;
        this.isExit = false;
    }

    /**
     * Execute the find command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return String : the response of the duke.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.printMatchingList(tasks.findTaskWithThisKeyword(this.keyword));
        assert this.isExit == false : "IsExit should be false";
        return response;
    }

}
