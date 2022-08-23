package jean.command;

import jean.storage.Storage;
import jean.task.Task;
import jean.task.TaskList;
import jean.ui.Ui;

public class MarkCommand extends Command {
    private int taskIndex;
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        Task curr = taskList.getTaskList().get(taskIndex);
        curr.setIsDone(true);
        ui.printMessage("\tI have marked it as done:"
                         + "\n\tJe l'ai marqué comme fait:"
                         + "\n\t" + curr.toString());
    }
}
