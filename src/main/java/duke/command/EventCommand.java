package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDurationException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidArgumentException;
import duke.task.Event;

/**
 * A class for the event command.
 */
public class EventCommand extends Command {

    /** The description of the event task as input by the user. */
    private final String description;

    /**
     * Constructor for the EventCommand class.
     *
     * @param description The description of the event task as input by the user.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the "event" command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws EmptyTaskException       If the followup text after the command is empty.
     * @throws InvalidArgumentException If the followup text after the command and description is not "/at".
     * @throws EmptyDurationException   If the followup text after "/at" is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyTaskException, InvalidArgumentException,
            EmptyDurationException {
        String[] split = this.description.split("/at ");
        if (this.description.trim().equals("") || split.length == 0 || split[0].equals("")) {
            throw new EmptyTaskException("event");
        }
        if (split[0].equals(this.description)) {
            throw new InvalidArgumentException("event", "/at");
        }
        if (split.length == 1) {
            throw new EmptyDurationException("event", "/at");
        }
        taskList.add(new Event(split[0].trim(), split[1]));
        System.out.println("  Added the event task: \n\t" + taskList.getLast());
        ui.printListCount();
    }
}
