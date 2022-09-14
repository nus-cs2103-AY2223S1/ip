package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;
import seedu.duke.Ui.Ui;
import seedu.duke.task.EventTask;

/**
 * Class for executing event commands
 */
public class EventCommand extends Command {
    private String details;
    private String time;

    public EventCommand(String details, String time) {
        this.details = details;
        this.time = time;
    }

    /**
     * Creates an EventTask and adds it to the list
     * @param list list to add the event to
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        int len = list.size();
        EventTask task = new EventTask(details, time);
        list.add(task);
        assert len == list.size() - 1 : "List size should have increased by 1";
        return Ui.added(task);
    }
}