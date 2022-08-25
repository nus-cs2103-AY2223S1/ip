package Command;

import java.util.ArrayList;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Task;

public class FindCommand extends Command {
    private String taskName;

    public FindCommand(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = t.findTask(taskName);
        t.printList(tasks);
    }
}
