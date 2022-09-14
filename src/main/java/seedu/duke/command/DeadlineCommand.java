package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;
import seedu.duke.ui.Ui;
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
        int len = list.size();
        DeadlineTask task = new DeadlineTask(details, time);
        list.add(task);
        assert len == list.size() - 1 : "List size should have increased by 1";
        return Ui.added(task);
    }
}
