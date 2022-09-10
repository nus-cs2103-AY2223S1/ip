package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui.Ui;
import seedu.duke.task.DeadlineTask;

/**
 * Class for executing Deadline command
 */
public class DeadlineCommand extends Command {
    private String details;
    private String time;

    public DeadlineCommand(String details, String time) {
        this.details = details;
        this.time = time;
    }

    /**
     * Creates a DeadlineTask and adds it to the list
     * @param list the list to add the task to
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        DeadlineTask task = new DeadlineTask(details, time);
        list.add(task);
        return Ui.added(task);
    }
}
