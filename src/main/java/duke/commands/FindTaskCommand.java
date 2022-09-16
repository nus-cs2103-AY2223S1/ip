package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

/**
 * A command that finds tasks that match a specified search string.
 */
public class FindTaskCommand extends Command {

    private TaskList tasks;
    private String searchTerm;

    /**
     * Constructs a new command to find tasks with descriptions containing
     * the specified search string.
     * @param tasks TaskList object in use by the app.
     * @param searchTerm The search string.
     */
    public FindTaskCommand(TaskList tasks, String searchTerm) {
        this.tasks = tasks;
        this.searchTerm = searchTerm;
    }

    /**
     * Finds the matching list of Tasks.
     * @return Response message.
     */
    @Override
    public String execute() {
        List<Task> matchingTasks = tasks.find(searchTerm);

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : matchingTasks) {
            sb.append(String.format("%d.%s\n", i, task.toString()));
            ++i;
        }
        sb.setLength(Math.max(sb.length() - 1, 0));
        return sb.toString();
    }

    /**
     * Does nothing.
     * @return A string indicating that there is nothing to undo.
     */
    @Override
    public String undo() {
        return "There is nothing to undo for your previous find task command.";
    }

}
