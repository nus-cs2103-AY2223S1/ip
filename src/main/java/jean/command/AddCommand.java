package jean.command;

import jean.storage.Storage;
import jean.task.Task;
import jean.task.TaskList;
import jean.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        ui.printMessage("\tadded / ajouté:"
                        + "\n\t\t" + this.task.toString());
        ui.printMessage("\tYou now have " + taskList.getNumberOfTasks() + " task(s)!"
                        + "\n\tVous avez " + taskList.getNumberOfTasks() + " tâche(s)!");
    }
}
