package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        TaskList filtered = tasks.filter(this.query);
        if (filtered.getSize() == 0) {
            throw new DukeException(Message.NO_RESULTS_FOUND);
        }
        return Ui.getTaskListString(filtered);
    }
}
