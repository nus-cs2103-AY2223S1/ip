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
 * Command to mark a task as done
 * Usage: mark /id -task number-
 * Task number according to number from list
 */
public class MarkCommand extends Command {
    private TaskModel taskModel;
    private TaskIdArgument taskId;

    /**
     * Creates new Mark command
     * @param taskModel TaskModel to use
     */
    public MarkCommand(TaskModel taskModel) {
        super("mark", "Marks a specified task as done");
        this.taskModel = taskModel;
        taskId = new TaskIdArgument();
    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        taskId = new TaskIdArgument(input);
        List<String> errs = Argument.validateArguments(taskId);

        if (errs.size() > 0) {
            return new CommandResponse(OutputLogger.errorOutput(errs));
        }

        try {
            Integer id = taskId.getParameter();
            Task markedTask = taskModel.markTask(id);
            return new CommandResponse(String.format("Nice! I've marked this task as done:%n%s",
                    markedTask.toString()));
        } catch (IllegalArgumentException ex) {
            return new CommandResponse(ex.getMessage());
        }
    }
    @Override
    public String getUsageDescription() {
        return makeUsage(taskId);
    }
}
