package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.tasks.Event;
import exceptions.InvalidCommandException;

/**
 * AddEventTaskCommand adds an event type task to task list of the instance of Duke given
 */
public class AddEventTaskCommand extends AddTimedTaskCommand {

    /**
     * Creates a new AddEventTaskCommand
     * @param duke the instance of Duke that spawned this command
     */
    public AddEventTaskCommand(Duke duke) {
        super(duke,
                "-on",
                "I cannot add an Event without a name.",
                "Please specify the date and time this event occurs on.");
    }

    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        super.parse(elements);
        Event task = new Event(taskName, dateAndTime);
        duke.getTaskList().add(task);
        duke.sendMessage("Okay, I've added this task " + task);
    }
}
