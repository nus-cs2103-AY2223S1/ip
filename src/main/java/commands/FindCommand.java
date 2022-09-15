package commands;

import byu.TaskList;
import byu.Ui;
import task.Task;

/**
 * A command to find tasks with names containing a given string.
 */
public class FindCommand extends Command {

    private final String substring;

    /**
     * Creates a FindCommand with a given string.
     *
     * @param substring the substring used to find tasks with names containing it.
     */
    public FindCommand(String substring) {
        this.substring = substring;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        StringBuilder response = new StringBuilder("These are the matching tasks I found:\n");
        for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
            Task task = tasks.getTask(i);
            if (task.getName().contains(this.substring)) {
                String taskInfo = String.format("%d. %s\n", i, task.toString());
                response.append(taskInfo);
            }
        }
        return response.toString();
    }
}
