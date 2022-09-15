package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.tasks.Deadline;
import exceptions.InvalidCommandException;

/**
 * AddDeadlineTaskCommand adds a deadline type task to task list of the instance of Duke given
 */
public class AddDeadlineTaskCommand extends AddTimedTaskCommand {

    /**
     * Creates a new AddDeadlineTaskCommand
     *
     * @param duke the instance of Duke that spawned this command
     */
    public AddDeadlineTaskCommand(Duke duke) {
        super(duke,
                "-by",
                "I cannot add an Deadline without a name.",
                "Please specify the due date and time of this deadline.");
    }

    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        super.parse(elements);
        Deadline task = new Deadline(taskName, dateAndTime);
        duke.getTaskList().add(task);
        duke.sendMessage("Okay, I've added this task " + task);
    }
}
