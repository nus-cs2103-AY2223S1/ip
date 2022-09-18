package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.time.LocalDateTime;

/**
 * Encapsulates a command to create a new deadline.
 */
public class DeadlineCommand extends Command {
    /** Stores the description and datetime of the deadline. */
    String desc;
    LocalDateTime datetime;

    /**
     * Constructor for DeadlineCommand.
     * @param desc Description of deadline
     * @param datetime Datetime of deadline
     */
    public DeadlineCommand(String desc, LocalDateTime datetime) {
        this.desc = desc;
        this.datetime = datetime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline tmp = new Deadline(desc, datetime);
        tasks.addDeadline(tmp);
        storage.write(tasks.toStringWritable());
        ui.showOutput("Got it. I added this task:");
        ui.showOutput("\t" + tmp);
        ui.showOutput("Now you have " + tasks.getLength() + " tasks in the list.");
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline tmp = new Deadline(desc, datetime);
        tasks.addDeadline(tmp);
        storage.write(tasks.toStringWritable());
        return "Got it. I added this task:\n" +
                "\t" + tmp + "\n" +
                "Now you have " + tasks.getLength() + " tasks in the list.";
    }
}
