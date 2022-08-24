package commands;

import storage.Storage;

// Import Tasks
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import ui.Ui;


public class AddCommand extends Command {
    private Task task;

    public AddCommand(String des, boolean marked) {
        this.task = new Todo(des);
        this.task.setStatusIcon(marked);
    }

    public AddCommand(String type, String des, String date, boolean marked) {
        switch (type) {
        case "deadline":
            this.task = new Deadline(des, date);
            break;
        case "event":
            this.task = new Event(des, date);
        }
        this.task.setStatusIcon(marked);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAdded(tasks, this.task);
        storage.updateStorage(tasks);
    }
}
