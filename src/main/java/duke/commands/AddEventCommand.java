package duke.commands;

import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeMissingArgumentException;

/**
 * Represents an executable <code>Command</code> to add <code>Event</code>.
 */
public class AddEventCommand extends Command {

    private static final int DESCRIPTION_INDEX = 6;

    private static final String MESSAGE_ARGUMENT_MISSING = "OOPS!!! The description and/or the time of an event "
            + "cannot be empty.";
    private static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n%s\nNow you have %d tasks "
            + "in the list.";
    private static final String REGEX_KEYWORD = " /at ";

    /**
     * Constructs a <code>AddEventCommand</code> command.
     *
     * @param description Input from the user.
     */
    public AddEventCommand(String description) {
        super(description);
    }

    /**
     * Adds a new <code>Event</code> into the <code>TaskList</code>
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeMissingArgumentException If the input is missing a description or time.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeMissingArgumentException {
        try {
            assert !description.substring(DESCRIPTION_INDEX).isBlank() : MESSAGE_ARGUMENT_MISSING;
            String[] str = description.substring(DESCRIPTION_INDEX).split(REGEX_KEYWORD);
            Event event = new Event(str[0], str[1], false);
            tasks.add(event);
            int numberOfTasks = tasks.getSize();
            String response = String.format(MESSAGE_SUCCESS, event, numberOfTasks);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException(MESSAGE_ARGUMENT_MISSING);
        }
    }
}
