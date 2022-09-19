package commands;

import java.util.List;

import arguments.Argument;
import arguments.DescriptionArgument;
import exceptions.DukeException;
import input.Input;
import output.OutputLogger;
import task.TaskModel;
import task.TaskResponse;



/**
 * Command for adding a Todo task
 * Usage: todo /d -description-
 */
public class TodoCommand extends Command {
    protected TaskModel taskModel;
    protected DescriptionArgument description;

    /**
     * Creates new Todo command
     * @param taskModel TaskModel ot use
     */
    public TodoCommand(TaskModel taskModel) {
        super("todo", "Adds a todo task");
        this.taskModel = taskModel;
        description = new DescriptionArgument();

    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        description = new DescriptionArgument(input);
        List<String> errs = Argument.validateArguments(description);

        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }

        TaskResponse res = taskModel.addTodo(description.getParameter());
        return new CommandResponse(TaskResponseFormatter.addedTask(res));
    }
    @Override
    public String getUsageDescription() {
        return makeUsage(description);
    }
}
