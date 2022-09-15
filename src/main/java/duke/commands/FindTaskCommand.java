package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;

public class FindTaskCommand extends Command {

    private TaskList tasks;
    private Ui ui;
    private String searchTerm;

    public FindTaskCommand(TaskList tasks, Ui ui, String searchTerm) {
        this.tasks = tasks;
        this.ui = ui;
        this.searchTerm = searchTerm;
    }

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
        return sb.toString();
    }

    @Override
    public String undo() {
        return "There is nothing to undo for your previous find task command.";
    }

}
