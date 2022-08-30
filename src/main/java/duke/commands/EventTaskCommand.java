package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.EventTask;
import duke.tasks.TaskList;

/**
 * EventTaskCommand has an at field for the timing of the event
 */
public class EventTaskCommand extends TaskCommand {

    protected LocalDate at;

    /**
     * Constructor for EventCommand with at field
     *
     * @param description Description of event
     * @throws DukeException either due to missing at field or poor date formatting
     */
    public EventTaskCommand(String description) throws DukeException {
        super(description);
        String[] eventlst = description.split("/at ", 2);
        if (eventlst.length < 2 || eventlst[1].equals("")) {
            throw new DukeException("Alamak! Fill in when the event is at...");
        }
        this.description = eventlst[0];
        try {
            LocalDate d1 = LocalDate.parse(eventlst[1]);
            this.at = d1;
        } catch (DateTimeParseException e) {
            throw new DukeException("Please fill in the date in this format yyyy-mm-dd");
        }
    }

    /**
     * Creates new EventTask and add to tasklist as well as print message to user
     *
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        EventTask task = new EventTask(this.description, this.at);
        tasks.add(task);
        storage.save(tasks);
        return super.getMessage(tasks, task);
    }
}
