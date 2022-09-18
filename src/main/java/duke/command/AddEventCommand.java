package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

/**
 * Adds an event task to the list when command is called.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String time;

    /**
     * Adds an event based on command received.
     *
     * @param description description of event.
     * @param time duration of event.
     */
    public AddEventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event;
        event = new Event(description, time);
        tasks.add(event);
        ui.outputMessage("Okay! (๑´ڡ`๑) I've added this task:\n"
                + event
                + "\nNow you have " + tasks.size()
                + " tasks in the list!");
        storage.save(tasks);
    }
}
