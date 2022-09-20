package dukeprogram.command.tasks;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.tasks.Deadline;
import dukeprogram.userinterface.Widget;
import exceptions.InvalidCommandException;
import utilities.StringUtilities;

/**
 * AddDeadlineTaskCommand adds a deadline type task to task list of the instance of Duke given
 */
public class AddDeadlineTaskCommand extends Command {

    protected String taskName;
    protected String dateAndTime;

    /**
     * Creates a new AddDeadlineTaskCommand
     *
     * @param duke the instance of Duke that spawned this command
     */
    public AddDeadlineTaskCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        taskName = StringUtilities.copyUntilDelimiter(elements, "/by");
        if (taskName.equals("")) {
            throw new InvalidCommandException("I cannot add an Deadline without a name.");
        }

        dateAndTime = StringUtilities.concatByDelimiter(elements, " ");
        if (dateAndTime.equals("")) {
            throw new InvalidCommandException("Please specify the due date and time of this deadline.");
        }
        Deadline task = new Deadline(taskName, dateAndTime);
        duke.getTaskList().add(task);
        duke.sendMessage("Okay, I've added this task ", new Widget(task.createLabelWidget()));
    }
}
