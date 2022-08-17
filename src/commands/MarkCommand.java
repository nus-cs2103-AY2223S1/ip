package commands;

import arguments.TaskIdArgument;
import input.Input;
import models.task.Task;
import models.task.TaskModel;

public class MarkCommand extends Command {
    TaskModel taskModel;
    TaskIdArgument taskId;

    public MarkCommand(TaskModel taskModel) {
        super("mark");
        this.taskModel = taskModel;
    }

    @Override
    public CommandResponse run(Input input) {
        taskId = new TaskIdArgument(input);

        try {
            taskId.validate();
            Integer id = taskId.getParameter();
            Task markedTask = taskModel.markTask(id);
            return new CommandResponse(String.format("Nice! I've marked this task as done:%n    %s", markedTask.toString()));
        } catch (IllegalArgumentException ex) {
            return new CommandResponse(ex.getMessage());
        }
    }
}
