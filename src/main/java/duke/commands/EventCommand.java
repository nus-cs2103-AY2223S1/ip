package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDateException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Adds an Event task to the list of tasks
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to add this event: \n";

    private Event event;

    /**
     * Constructs an Event command instance
     *
     * @param description The description of the task
     * @param at The date of the event
     * @throws DukeInvalidDateException if the date of the event does not follow
     *     the correct date format
     */
    public EventCommand(String description, String at) throws DukeInvalidDateException {
        super();
        event = new Event(description, at);
    }

    /**
     * Returns a boolean value true if the command is a bye command,
     * false otherwise.
     *
     * @return a boolean value on whether the command is a bye command
     */
    @Override
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Executes the command to add the specified Event task to the
     * list of tasks
     *
     * @param tasks The current list of tasks
     * @param ui The Ui instance to return the result to the user
     * @param storage The Storage instance to store the result to local storage
     * @return the string representation of the execution result
     * @throws DukeException if errors are encountered during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(event);
        String result = MESSAGE_SUCCESS + event + " " + tasks.showNumberOfTasks();
        ui.showMessage(result);
        return result;
    }
}