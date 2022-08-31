package duke.command;

import duke.InvalidDateException;
import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.DateTimeException;

/**
 * EventCommand adds an Event to tasks.
 */
public class EventCommand extends Command {
    private String eventDescription;
    private String at;

    /**
     * Constructor for EventCommand.
     * @param eventDescription event description.
     * @param at when the event is occurring.
     */
    public EventCommand(String eventDescription, String at) {
        super();
        this.eventDescription = eventDescription;
        this.at = at;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException {
        try {
            Event event = new Event(eventDescription, at);
            tasks.addToTaskList(event);
            storage.save(tasks.getTasks());
            return String.format("Got it. I've added this task:\n" +
                            "added: %s\n" +
                            "Now you have %s task%s in the list.",
                    event.toString(),
                    String.valueOf(tasks.getSize()),
                    tasks.getSize() == 1 ? "" : "s");
        } catch (DateTimeException e) {
            throw new InvalidDateException();
        }
    }
}
