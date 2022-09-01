package duke;

/**
 * Represents a command to find matching task in the current tasklist that contains the keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the find command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return boolean false (true if exit and false if not exit).
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTaskWithThisKeyword(this.keyword);
        return false;
    }

}
