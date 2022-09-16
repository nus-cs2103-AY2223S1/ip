package duke.command;

import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for find keyword
 * which will find a task based on the
 * string input given by the user
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates a new find command
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    /**
     * Executes the find command which find a task
     * based on the input String given
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns String that contains message to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.find(keyword);
        return ui.find(listOfTasks);
    }
}
