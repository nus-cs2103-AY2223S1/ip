package duke.command;

import java.util.List;

import duke.DukeException;
import duke.task.Task;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        super.isExit = false;
        this.query = query;
    }

    @Override
    public void execute() throws DukeException {
        List<Task> matched = Command.taskList.find(this.query);
        Command.ui.displayTaskListSearch(matched);
    }
}
