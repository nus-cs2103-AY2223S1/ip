package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui.Ui;
import seedu.duke.task.ToDoTask;

/**
 * Class to execute todo command
 */
public class ToDoCommand extends Command {
    private String details;

    public ToDoCommand(String details) {
        this.details = details;
    }

    /**
     * Creates a ToDoTask and adds it to the list
     * @param list
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        ToDoTask task = new ToDoTask(details);
        list.add(task);
        return Ui.added(task);
    }
}
