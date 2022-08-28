package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.task.Todo;
import unc.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String input) {
        this.description = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        ui.addTodo(taskList, newTodo);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
