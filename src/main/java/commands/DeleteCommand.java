package commands;

import java.util.List;

import arguments.Argument;
import arguments.TaskIdArgument;
import exceptions.DukeException;
import input.Input;
import output.OutputLogger;
import task.TaskModel;
import task.TaskResponse;


/**
 * Class for Delete command
 */
public class DeleteCommand extends Command {
    private TaskModel taskModel;
    private TaskIdArgument taskIdArgument;

    /**
     * Creates new Delete command
     * @param taskModel TaskModel to use
     */
    public DeleteCommand(TaskModel taskModel) {
        super("delete", "Deletes the specified task.");
        this.taskModel = taskModel;
        taskIdArgument = new TaskIdArgument();
    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        taskIdArgument = new TaskIdArgument(input);
        List<String> errs = Argument.validateArguments(taskIdArgument);
        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }

        TaskResponse res = taskModel.deleteTask(taskIdArgument.getParameter());
        return new CommandResponse(TaskResponseFormatter.deletedTask(res));
    }
    @Override
    public String getUsageDescription() {
        return makeUsage(taskIdArgument);
    }
}
