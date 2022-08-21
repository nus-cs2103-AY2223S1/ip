package jean.command;

import jean.storage.Storage;
import jean.task.Task;
import jean.task.TaskList;
import jean.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    int taskIndex;
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task curr = taskList.getTaskList().get(this.taskIndex);
        taskList.getTaskList().remove(this.taskIndex);
        System.out.println("\tI have deleted the task:"
                           + "\n\tJe l'ai supprimé:"
                           + "\n\t" + curr.toString()
                           + "\n\tYou now have " + taskList.getNumberOfTasks() + " tasks remaining!"
                           + "\n\tIl vous reste maintenant " + taskList.getNumberOfTasks() + " tâches!");
    }
}
