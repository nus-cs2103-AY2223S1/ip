package roger.commands;

import java.util.List;

import roger.storage.Storage;
import roger.tasks.TaskList;
import roger.tasks.Task;


public class FindCommand extends Command {
    protected String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> tasksMatched = tasks.search(this.query);

        if (tasksMatched.isEmpty()) {
            return "No tasks matching that string.";
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task: tasksMatched) {
            response.append(String.valueOf(tasks.getTaskNum(task)) + ". " + task.toString() + "\n");
        }
        return response.toString();
    }
}
