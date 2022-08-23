package roger.commands;

import java.util.List;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Task;


public class FindCommand extends Command {
    protected String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksMatched = tasks.search(this.query);

        if (tasksMatched.isEmpty()) {
            ui.show("No tasks matching that string.");
            return;
        }

        ui.show("Here are the matching tasks in your list:");
        for (Task task: tasksMatched) {
            ui.show(String.valueOf(tasks.getTaskNum(task)) + ". " + task.toString());
        }
    }
}
