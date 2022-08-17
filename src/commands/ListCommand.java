package commands;

import input.Input;
import models.task.Task;
import models.task.TaskModel;
import output.OutputLogger;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> stringList = allTasks.stream().map(t -> t.toString()).collect(Collectors.toList());
        return new CommandResponse(OutputLogger.numberedOutput(stringList));
    }
}
