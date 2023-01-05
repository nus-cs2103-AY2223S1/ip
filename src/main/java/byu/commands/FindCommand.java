package byu.commands;

import byu.task.Task;
import byu.util.Response;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to find tasks with names containing a given string.
 */
public class FindCommand extends Command {

    private final String substring;

    /**
     * Creates a FindCommand with a given string.
     *
     * @param substring the {@code String} used to find tasks with names containing it.
     */
    public FindCommand(String substring) {
        this.substring = substring;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
    }

    @Override
    public Response generateResponse(TaskList tasks) {
        StringBuilder response = new StringBuilder("These are the matching tasks I found:\n");
        for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
            Task task = tasks.getTask(i);
            if (task.matches(this.substring)) {
                String taskInfo = String.format("%d. %s\n", i, task);
                response.append(taskInfo);
            }
        }
        String output = response.toString();
        return new Response(output, false, false);
    }
}
