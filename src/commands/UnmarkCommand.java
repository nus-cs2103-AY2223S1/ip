package commands;

import arguments.Argument;
import arguments.TaskIdArgument;
import input.Input;
import models.task.Task;
import models.task.TaskModel;
import output.OutputLogger;

import java.util.List;

public class UnmarkCommand extends Command {
    TaskModel taskModel;
    TaskIdArgument taskId;

    public UnmarkCommand(TaskModel taskModel) {
        super("unmark");
        this.taskModel = taskModel;
    }

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
            return new CommandResponse(String.format("Alright, I've marked this task as not done yet:%n%s", markedTask.toString()));
        } catch (IllegalArgumentException ex) {
            return new CommandResponse(ex.getMessage());
        }
    }
}
