package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.commands.Command;
import roger.tasks.Task;

public class MarkCommand extends Command {
    protected int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        try {
            task = tasks.get(this.taskNum);
        } catch (IndexOutOfBoundsException e) {
            ui.show("That task does not exist!");
            return;
        }
        task.markAsDone();
        ui.showcase("Fuiyoh, nephew so efficient! Finished this task:", task.toString());
    }
}

