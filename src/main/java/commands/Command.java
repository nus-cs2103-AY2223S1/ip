package command;

import drivers.Storage;
import drivers.TaskList;
import drivers.UI;
import exceptions.TumuException;
import tasks.Task;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws TumuException;

    protected void addTaskType(Task task, TaskList tasks, UI ui) {
        ui.notifyUser("I've added a task into your list:\n\t\t" + task);
        tasks.addTask(task);
        ui.notifyUser(String.format("You have %d task(s) in the list.", tasks.getListSize()));
    }

    protected void saveUserTasks(Storage storage, TaskList tasks) {
        storage.saveData(tasks.getTasks());
    }
}
