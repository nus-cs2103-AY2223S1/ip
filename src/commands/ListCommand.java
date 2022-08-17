package commands;

import input.Input;
import models.task.Task;
import models.task.TaskModel;

import java.util.Collection;
import java.util.List;

public class ListCommand extends Command {
    protected TaskModel taskModel;

    public ListCommand(TaskModel taskModel) {
        super("list");
        this.taskModel = taskModel;
    }

    @Override
    public CommandResponse run(Input input) {
        List<Task> allTasks = taskModel.getAllTasks();
        if (allTasks.size() == 0) return new CommandResponse("No tasks added.");

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < allTasks.size(); i++) {
            int id = i + 1;
            String taskItem = String.format("%d. %s%n", i + 1, allTasks.get(i));
            out.append(taskItem);
        }

        return new CommandResponse(out.toString());
    }
}
