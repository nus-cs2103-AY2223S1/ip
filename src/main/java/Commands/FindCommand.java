package Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import Tasks.Task;

import java.util.List;

public class FindCommand extends Command {
    private String name;

    public FindCommand(String name) {
        super();
        this.name = name;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        List<Task> lst = t.findTask(name);
        t.printList(lst);
    }

}
