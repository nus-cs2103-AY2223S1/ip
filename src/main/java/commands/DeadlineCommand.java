package commands;

import arguments.Argument;
import arguments.ByArgument;
import arguments.DescriptionArgument;
import exceptions.DukeException;
import input.Input;
import task.TaskModel;
import task.TaskResponse;
import output.OutputLogger;

import java.util.List;

/**
 * Command to add a Deadline task
 * usage: deadline /d -description- /by -date-
 */
public class DeadlineCommand extends Command {
    protected TaskModel taskModel;
    protected DescriptionArgument description;
    protected ByArgument by;

    public DeadlineCommand(TaskModel taskModel) {
        super("deadline");
        this.taskModel = taskModel;

    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        description = new DescriptionArgument(input);
        by = new ByArgument(input);

        List<String> errs = Argument.validateArguments(description, by);

        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }

        TaskResponse res = taskModel.addDeadline(description.getParameter(), by.getParameter());
        return new CommandResponse(TaskResponseFormatter.addedTask(res));
    }
}
