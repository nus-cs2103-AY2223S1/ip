package duke;

import java.util.Objects;

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

    /**
     * Checks equality to another Object.
     *
     * @param o Other Object.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        FindCommand that = (FindCommand) o;
        return Objects.equals(keyword, that.keyword);
    }

}
