package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Task;

public class UnmarkCommand extends Command {
    protected int taskNum;

    public UnmarkCommand(int taskNum) {
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
        task.unmarkAsDone();
        ui.showcase("Haven't done yet, mark what mark? Unmarked this task:", task.toString());
    }
}

