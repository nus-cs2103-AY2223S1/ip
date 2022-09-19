package commands;

import java.util.List;

import arguments.Argument;
import arguments.AtArgument;
import arguments.DescriptionArgument;
import exceptions.DukeException;
import input.Input;
import output.OutputLogger;
import task.TaskModel;
import task.TaskResponse;




/**
 * Command to add an Event task
 * Usage: event /d -description- /at -date-
 */
public class EventCommand extends Command {
    protected TaskModel taskModel;
    protected DescriptionArgument description;
    protected AtArgument at;

    /**
     * Creates new Event command
     * @param model TaskModel to use
     */
    public EventCommand(TaskModel model) {
        super("event", "Adds an event task");
        this.taskModel = model;
        description = new DescriptionArgument();
        at = new AtArgument();
    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        description = new DescriptionArgument(input);
        at = new AtArgument(input);
        List<String> errs = Argument.validateArguments(description, at);

        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }

        TaskResponse res = taskModel.addEvent(description.getParameter(), at.getParameter());
        return new CommandResponse(TaskResponseFormatter.addedTask(res));
    }
    @Override
    public String getUsageDescription() {
        return makeUsage(description, at);
    }
}
