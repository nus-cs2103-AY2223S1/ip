package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Executes the command to find a task.
 *
 * @author Lim Ai Lin
 */
public class FindCommand extends Command {

    private final String[] FIND_MATCH;

    public FindCommand(String[] str) {
        this.FIND_MATCH = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String match = null;
        try {
            match = FIND_MATCH[1];
        } catch (Exception e) {
            ui.emptyDescription();
        }
        ArrayList<Task> matching = tasks.find(match);
        return ui.match(matching);
    }
}
