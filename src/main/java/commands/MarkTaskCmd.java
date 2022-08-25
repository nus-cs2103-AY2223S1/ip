package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.UI;
import exceptions.TumuException;
import tasks.Task;

public class MarkTaskCmd extends Command {
    private int taskIndex;

    public MarkTaskCmd(String body) throws NumberFormatException {
        taskIndex = Integer.parseInt(body);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {
        Task task = tasks.markTask(taskIndex);
        if (task != null) {
            ui.notifyUser("Alright, I have marked this task as done:\n\t" + task);
        }
        saveUserTasks(storage, tasks);
    }
}
