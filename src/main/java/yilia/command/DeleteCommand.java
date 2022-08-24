package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.Task;
import yilia.task.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.remove(index);
            ui.showDeleteStatus(task, tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " out of bounds\nPlease input another index");
        }
    }
}
