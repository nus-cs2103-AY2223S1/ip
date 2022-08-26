package commands;

import java.util.List;
import java.util.stream.Collectors;

import input.Input;
import output.OutputLogger;
import task.Task;
import task.TaskModel;




/**
 * Command to list all current tasks
 * Usage: list
 */
public class ListCommand extends Command {
    protected TaskModel taskModel;

    /**
     * Creates new List command
     * @param taskModel TaskModel to use
     */
    public ListCommand(TaskModel taskModel) {
        super("list");
        this.taskModel = taskModel;
    }

    @Override
    public CommandResponse run(Input input) {
        List<Task> allTasks = taskModel.getAllTasks();
        if (allTasks.size() == 0) {
            return new CommandResponse("No tasks added.");
        }
        List<String> stringList = allTasks.stream().map(Task::toString).collect(Collectors.toList());
        return new CommandResponse(OutputLogger.numberedOutput(stringList));
    }
}
