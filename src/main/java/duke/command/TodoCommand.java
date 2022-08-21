package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command{
    private String description;
    
    public static String getFormat() {
        return "todo <String>";
    }
    
    public TodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(this.description);
        tasks.add(todo);
        ui.showAddTask(todo, tasks);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
