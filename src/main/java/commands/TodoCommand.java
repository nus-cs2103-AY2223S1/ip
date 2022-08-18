package commands;

import arguments.Argument;
import arguments.DescriptionArgument;
import exceptions.DukeException;
import input.Input;
import task.TaskModel;
import task.TaskResponse;
import output.OutputLogger;

import java.util.List;

/**
 * Command for adding a Todo task
 * Usage: todo /d -description-
 */
public class TodoCommand extends Command {
    protected TaskModel taskModel;
    protected DescriptionArgument description;


    public TodoCommand(TaskModel taskModel) {
        super("todo");
        this.taskModel = taskModel;

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
}
