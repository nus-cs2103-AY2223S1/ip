package Duke.commands;


import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Duke.tasks.Task;
import Duke.tasks.Todo;
import java.io.IOException;

public class TodoCommands extends Executor {


    public TodoCommands(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ui.printLine("I've added this task:");
        Task addedTask = tasks.addTask(new Todo(description));
        ui.printLine(addedTask);
        storage.addTask(addedTask);
        super.execute(tasks, ui, storage);
    }
}
