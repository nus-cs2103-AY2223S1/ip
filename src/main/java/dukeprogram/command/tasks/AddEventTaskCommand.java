package dukeprogram.command.tasks;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.tasks.Event;
import dukeprogram.userinterface.Widget;
import exceptions.InvalidCommandException;
import utilities.StringUtilities;

/**
 * AddEventTaskCommand adds an event type task to task list of the instance of Duke given
 */
public class AddEventTaskCommand extends Command {

    protected String taskName;
    protected String startTime;
    protected String endTime;

    /**
     * Creates a new AddTimedTaskCommand
     * @param duke the instance of Duke that spawned this command
     */
    public AddEventTaskCommand(Duke duke) {
        super(duke);
    }

    /**
     * Parses the elements given and adds a newly created event task type
     * @param elements the continued iterator of elements
     * @throws InvalidCommandException if there is not enough arguments given
     */
    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        taskName = StringUtilities.copyUntilDelimiter(elements, "/from");
        if (taskName.equals("")) {
            throw new InvalidCommandException("I cannot add an Event without a name.");
        }

        startTime = StringUtilities.copyUntilDelimiter(elements, "/to");
        if (startTime.equals("")) {
            throw new InvalidCommandException("Please specify the start and end time of this event.");
        }

        endTime = StringUtilities.concatByDelimiter(elements, " ");
        if (endTime.equals("")) {
            endTime = startTime;
        }

        Event task = new Event(taskName, startTime, endTime);
        duke.getTaskList().add(task);
        duke.sendMessage("Okay, I've added this task ", new Widget(task.createLabelWidget()));
    }
}
