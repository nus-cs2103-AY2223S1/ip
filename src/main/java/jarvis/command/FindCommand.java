package jarvis.command;

import java.util.List;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.task.TaskList;

/**
 * FindCommand --- find tasks.
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
     * @param tasks the list of tasks.
     * @param storage stores the tasks locally.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JarvisException {
        List<Task> found = tasks.findTasks(super.getDescription());

        if (found.size() > 0) {
            StringBuilder output = new StringBuilder();
            output.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < found.size(); i++) {
                output.append(String.format("\t %d. %s", i + 1, found.get(i)));
                if (i + 1 < found.size()) {
                    output.append("\n");
                }
            }
            return output.toString();
        } else {
            return "Sorry. No matching tasks found.";
        }
    }
}

