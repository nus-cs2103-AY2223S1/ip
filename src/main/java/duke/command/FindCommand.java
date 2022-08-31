package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Command to find a task by searching a keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Constructor for the FindCommand.
     * @param keyword The keyword used to find list of tasks that matches it.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Return a list of tasks from the task list that matches with the keyword the user
     * input.
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The list of tasks that matches with the user input keyword.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        ArrayList<Task> matchedTasks = task.findTasks(this.keyword);
        String listOfMatchedTasks = "Here are the matching tasks in your list\n";
        for (int i = 0; i < matchedTasks.size(); i++) {
            listOfMatchedTasks += (i + 1) + ". " + matchedTasks.get(i).taskInfo() + "\n";
        }
        return listOfMatchedTasks;
    }

}
