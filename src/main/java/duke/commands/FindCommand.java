package duke.commands;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * The FindCommand class represents user command find.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    /**
     * Constructor of FindCommand that takes in
     * a string to represent keyword.
     * @param keyword Specified keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(foundTasks);
    }
}
