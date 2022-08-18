package commands;

import arguments.Argument;
import arguments.TaskIdArgument;
import exceptions.DukeException;
import input.Input;
import output.OutputLogger;
import task.TaskModel;
import task.TaskResponse;

import java.util.List;

public class DeleteCommand extends Command {
    TaskModel taskModel;
    TaskIdArgument taskIdArgument;
    public DeleteCommand(TaskModel taskModel) {
        super("delete");
        this.taskModel = taskModel;
    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        taskIdArgument = new TaskIdArgument(input);
        List<String> errs = Argument.validateArguments(taskIdArgument);
        if (errs.size() > 0) return new CommandResponse(OutputLogger.errorOutput(errs));

        TaskResponse res = taskModel.deleteTask(taskIdArgument.getParameter());
        return new CommandResponse(TaskResponseFormatter.deletedTask(res));
    }
}
