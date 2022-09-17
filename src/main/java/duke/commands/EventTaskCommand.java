package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DateDukeException;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.tasks.EventTask;
import duke.tasks.TaskList;

/**
 * EventTaskCommand has an at field for the timing of the event
 */
public class EventTaskCommand extends TaskCommand {

    protected LocalDate at;

    /**
     * Creates an EventTaskCommand
     *
     * @param description Description of event
     * @throws DukeException either due to missing at field or poor date formatting
     */
    public EventTaskCommand(String description) throws DukeException {
        super(description);
        assert description.split(" ")[0].equals("event") : "Keyword should be event for EventTaskCommand";

        String[] eventList = this.description.split("/at ", 2);

        if (eventList.length < 2 || eventList[1].equals("")) {
            throw new DukeException("Please tell me when the event is at...");
        }

        this.description = eventList[0];

        try {
            this.at = LocalDate.parse(eventList[1]);
        } catch (DateTimeParseException e) {
            throw new DateDukeException();
        }
    }

    /**
     * Creates new EventTask and add to tasklist as well as print message to user
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        EventTask task = new EventTask(this.description, this.at);
        tasks.add(task);
        storage.save(tasks);
        return super.getMessage(tasks, task);
    }
}
