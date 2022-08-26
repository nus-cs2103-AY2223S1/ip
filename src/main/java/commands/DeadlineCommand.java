package commands;

import java.util.List;

import arguments.Argument;
import arguments.DatetimeArgument;
import arguments.DescriptionArgument;
import exceptions.DukeException;
import input.Input;
import output.OutputLogger;
import task.TaskModel;
import task.TaskResponse;




/**
 * Command to add a Deadline task
 * usage: deadline /d -description- /by -date-
 */
public class DeadlineCommand extends Command {
    protected TaskModel taskModel;
    protected DescriptionArgument description;
    protected DatetimeArgument by;

    /**
     * Creates new Deadline command
     * @param taskModel TaskModel to use
     */
    public DeadlineCommand(TaskModel taskModel) {
        super("deadline");
        this.taskModel = taskModel;

    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        description = new DescriptionArgument(input);
        by = new DatetimeArgument(input);

        List<String> errs = Argument.validateArguments(description, by);

        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }

        TaskResponse res = taskModel.addDeadline(description.getParameter(), by.getParameter());
        return new CommandResponse(TaskResponseFormatter.addedTask(res));
    }
}
