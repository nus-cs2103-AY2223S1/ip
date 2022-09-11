package Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import Tasks.Task;

import java.util.List;

/**
 * Find command which inherits from Command
 */
public class FindCommand extends Command {
    private String name;

    public FindCommand(String name) {
        super();
        this.name = name;
    }

    @Override
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        List<Task> lst = t.findTask(name);
        return t.printList(lst);
    }

}
