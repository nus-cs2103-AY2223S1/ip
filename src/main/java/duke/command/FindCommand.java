package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String toFind;

    public FindCommand(String toFind) {
        super();
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.find(toFind);
        if (foundTasks.size() == 0) {
            throw new DukeException(String.format("No tasks found containing \"%s\"", toFind));
        }
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            listString.append(String.format("%d.%s\n", i + 1, foundTasks.get(i).toString()));
        }
    }
}
