package duke;

/**
 * Represents a Command to find tasks in Duke that matches a certain keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructor of FindCommand with keyword to match.
     *
     * @param keyword Keyword to match.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Run the FindCommand, print all tasks in Duke that matches the keyword to the UI.
     *
     * @param duke Duke instance to run the AddCommand at.
     */
    @Override
    public void run(Duke duke) {
        duke.findTasks(keyword);
    }
}
