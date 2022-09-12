package jarvis.command;

import java.util.List;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * FindCommand --- command to find tasks by description.
 */
public class FindCommand extends Command {
    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     *
     * @param storage stores the tasks locally.
     * @param tasks the list of tasks.
     * @param ui prints feedback.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws JarvisException {
        List<Task> tasksFound = tasks.findTasks(super.getDescription());

        if (tasksFound.size() > 0) {
            StringBuilder output = new StringBuilder();
            output.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasksFound.size(); i++) {
                output.append(String.format("\t %d. %s", i + 1, tasksFound.get(i)));
                if (i + 1 < tasksFound.size()) {
                    output.append("\n");
                }
            }
            return output.toString();
        } else {
            return "Sorry. No matching tasks found.";
        }
    }
}

