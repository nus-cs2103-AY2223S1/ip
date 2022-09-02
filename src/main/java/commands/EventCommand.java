package commands;

import exceptions.DukeException;
import exceptions.DukeInvalidDateException;
import storage.Storage;
import tasks.TaskList;
import tasks.Event;
import ui.Ui;

/**
 * Adds a person to the address book.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to add this event: \n";

    private Event event;

    public EventCommand(String description, String at) throws DukeInvalidDateException {
        super();
        event = new Event(description, at);
    }

    public EventCommand(String description, String at, boolean isMarked) throws DukeInvalidDateException {
        super();
        event = new Event(description, at);
        event.markAsDone();
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(event);
        ui.showMessage(MESSAGE_SUCCESS + event + " " + tasks.showNumberOfTasks());
    }
}