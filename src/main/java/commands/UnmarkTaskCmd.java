package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.UI;
import exceptions.TumuException;
import tasks.Task;

public class UnmarkTaskCmd extends Command {
    private int taskIndex;

    public UnmarkTaskCmd(String body) throws NumberFormatException {
        taskIndex = Integer.parseInt(body);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {
        Task task = tasks.unmarkTask(taskIndex);
        if (task != null) {
            ui.notifyUser("Alright, I have unmarked this task:\n\t" + task);
        }
        saveUserTasks(storage, tasks);
    }
}
