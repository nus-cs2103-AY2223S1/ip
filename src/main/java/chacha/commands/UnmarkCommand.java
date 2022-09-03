package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }   

    @Override
    public void execute(TaskList taskList, Ui ui) {
                Task task = taskList.get(taskIndex);
                task.unmarkAsDone();
                ui.printUnmark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}