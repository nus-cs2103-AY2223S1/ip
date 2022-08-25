package commands;

import byu.ToDoList;
import byu.Ui;

import task.Task;

public class AddCommand extends Command {

    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(ToDoList tasks, Ui ui) {
        tasks.addTask(task);
    }

    public boolean isExit() {
        return false;
    }
}
