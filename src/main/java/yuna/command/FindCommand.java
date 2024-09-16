package yuna.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import yuna.exception.YunaException;
import yuna.storage.Storage;
import yuna.task.Task;
import yuna.task.TaskList;
import yuna.ui.Ui;


/**
 * Command that finds a task by searching for a keyword.
 *
 * @author Bryan Ng Zi Hao
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword in the task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Checks if the tasks contains the keyword.
     *
     * @param ui The ui used to deal with interactions with the user.
     * @param storage The storage used to store the data.
     * @param taskList The list of tasks to check from.
     * @return String representation of Yuna's reply.
     * @throws YunaException If there is an execution error.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws YunaException {
        ArrayList<Task> filteredTasks = taskList.getTasks().stream()
                .filter(task -> task.contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredTasks.size() > 0) {
            String output = "Here you go!\n";
            for (int i = 0; i < filteredTasks.size(); i++) {
                output += (i + 1) + ". " + filteredTasks.get(i) + "\n";
            }
            assert output.length() > 0 : "The output of execute should always return a message.";
            return output;
        } else {
            return "I can't find anything like that :(";
        }
    }

    /**
     * Checks if the command results in Yuna to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
