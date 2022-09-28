package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;

import java.time.LocalDateTime;

/**
 * Encapsulates a class to create an event.
 */
public class EventCommand extends Command {
    /** Stores the description and datetime of the event. */
    String desc;
    LocalDateTime datetime;

    /**
     * Constructor for EventCommand.
     * @param desc Description of event
     * @param datetime Datetime of event
     */
    public EventCommand(String desc, LocalDateTime datetime) {
        this.desc = desc;
        this.datetime = datetime;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event tmp = new Event(desc, datetime);
        tasks.addEvent(tmp);
        storage.write(tasks.toStringWritable());
        return "Got it. I added this task:\n" +
                "\t" + tmp + "\n" +
                "Now you have " + tasks.getLength() + " tasks in the list.";
    }
}
