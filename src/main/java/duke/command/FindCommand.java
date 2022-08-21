package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.findTask(keyword);
        if (foundTasks.isEmpty()) {
            throw new DukeException("\tNo matching tasks found!");
        } else {
            ui.showFind(foundTasks);
        }
    }
}
