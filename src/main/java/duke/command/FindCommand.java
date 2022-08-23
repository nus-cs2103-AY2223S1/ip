package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, UI ui, TaskList tasks) throws DukeException {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        ui.print("Tasks containing \"" + keyword + "\":\n" + foundTasks);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            FindCommand other = (FindCommand) o;
            return this.keyword.equals(other.keyword);
        }
        return false;
    }
}
