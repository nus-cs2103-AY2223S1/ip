package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.Event;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to add an Event object.
 */
public class AddEventCommand extends Command {
    public static final String KEYWORD = "event";
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nAdds an event with a given date and time of event."
        + "\n\nRequired: /at The delimiter to detect the date and time"
        + "\n\nExample: event buy coffee /at 10 Oct 2021 10:30";
    private Event event;
    private boolean helpShown;

    /**
     * Represents the constructor method.
     *
     * @param event The event to be added
     * @param helpShown Whether to show help or not
     */
    private AddEventCommand(Event event, boolean helpShown) {
        this.event = event;
        this.helpShown = helpShown;
    }

    /**
     * Overloads the constructor method to add an event.
     *
     * @param event The event to be added
     */
    public AddEventCommand(Event event) {
        this(event, false);
    }

    /**
     * Overloads the constructor method to show help.
     */
    public AddEventCommand() {
        this(null, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds an Event task class to the task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @return The stringtified UI output
     * @throws StashyException If any exception is caught
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (this.helpShown) {
            return HELP_MESSAGE;
        } else {
            tasks.add(this.event);
            return "There, we have a new event:\n  " + tasks.get(tasks.size() - 1)
                + "\nYou have " + tasks.size() + " task(s) in the list.";
        }
    }
}
