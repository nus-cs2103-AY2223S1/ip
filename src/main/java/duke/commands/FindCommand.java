package duke.commands;

import java.util.List;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the command to find matching tasks whose descriptions contain the given keyword(s).
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String[] keywords;

    /**
     * Constructor for a FindCommand.
     * @param keywords The keyword(s) to check each Task's description for.
     */
    public FindCommand(String[] keywords) {
        super();
        this.keywords = keywords;
    }

    /**
     * Finds matching tasks whose description contains any of the given keyword(s).
     * @param taskList List of tasks.
     * @param ui Shows the list of tasks whose description contains any of the given keyword(s).
     * @param storage Saves the modified list of tasks.
     * @return A message indicating the matching tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchingTasks = taskList.getTasksWithKeywords(keywords);
        return ui.showMatchingTasks(matchingTasks);
    }
}
