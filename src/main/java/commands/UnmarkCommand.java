package commands;

import java.util.List;

import arguments.Argument;
import arguments.TaskIdArgument;
import exceptions.DukeException;
import input.Input;
import output.OutputLogger;
import task.Task;
import task.TaskModel;




/**
 * Command for unmarking a task
 * Usage: unmark /id -task number-
 */
public class UnmarkCommand extends Command {
    protected TaskModel taskModel;
    protected TaskIdArgument taskId;

    /**
     * Creates new Unmark command
     * @param taskModel TaskModel to use
     */
    public UnmarkCommand(TaskModel taskModel) {
        super("unmark", "Unmarks the specified task from done to not done");
        this.taskModel = taskModel;
        taskId = new TaskIdArgument();
    }

    // Template: initialise arguments, validate, return errorOutput if errs. then get parameters,
    // call relevant model method
    // return commandresponse for success and exception cases
    @Override
    public CommandResponse run(Input input) {
        taskId = new TaskIdArgument(input);
        List<String> errs = Argument.validateArguments(taskId);

        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }

        try {
            Integer id = taskId.getParameter();
            Task markedTask = taskModel.unmarkTask(id);
            return new CommandResponse(String.format("Alright, I've marked this task as not done yet:%n%s",
                    markedTask.toString()));
        } catch (IllegalArgumentException | DukeException ex) {
            return new CommandResponse(ex.getMessage());
        }
    }
    @Override
    public String getUsageDescription() {
        return makeUsage(taskId);
    }
}
