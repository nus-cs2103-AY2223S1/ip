package duke.command;

import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command to add an event.
 */
public class EventCommand extends Command {
    private String task;
    private String on;

    /**
     * Creates a new EventCommand.
     *
     * @param task the description of the event
     * @param on the date of the event
     */
    public EventCommand(String task, String on) {
        super();
        this.task = task;
        this.on = on;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Event(task, on));
        return ui.printAddTaskSuccessfully(tasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof EventCommand == false) {
            return false;
        }
        EventCommand that = (EventCommand) o;
        return task.equals(that.task)
                && on.equals(that.on);
    }
}
