package john.commands;

/**
 * Represents a command to find tasks containing a keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String FORMAT = "find <keyword>";

    private String findParams;

    /**
     * Constructor for a FindCommand.
     * @param findParams The keyword to search for.
     */
    public FindCommand(String findParams) {
        this.findParams = findParams;
    }

    /**
     * Returns a string representation of the tasks matching the keywords.
     * @return A string representation of the tasks matching the keywords.
     */
    @Override
    public String execute() {
        String[] tasksToShow = tasklist.findTasks(this.findParams);
        if (tasksToShow == null) {
            return ui.showNoTasks(tasklist, this.findParams);
        }
        return ui.showTasks(tasksToShow);
    }
}
