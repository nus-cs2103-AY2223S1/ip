package commands;

import input.Input;
import models.task.Task;
import models.task.TaskModel;

public class AddCommand extends Command {
    protected TaskModel taskModel;

    public AddCommand(TaskModel taskModel) {
        super("");
        this.taskModel = taskModel;

    }

    @Override
    public CommandResponse run(Input input) {
        Task newTask = taskModel.addTask(input.getInputString());
        return new CommandResponse(String.format("added: %s", newTask.getDescription()));
    }
}
