package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.tasks.DeadlineTask;
import duke.tasks.TaskList;

/**
 * DeadlineCommand which handles creation and message of DeadlineTask
 */
public class DeadlineTaskCommand extends TaskCommand {

    private final LocalDate by;

    /**
     * Constructor for DeadlineCommand which includes description of task and when
     * the task needed to be completed by
     *
     * @param description Description of task
     * @throws DukeException either due to missing by field or poor date formatting
     */
    public DeadlineTaskCommand(String description) throws DukeException {
        super(description);
        String[] eventlst = description.split("/by ", 2);

        if (eventlst.length < 2 || eventlst[1].equals("")) { // Guard clasuse
            throw new DukeException("Alamak! Fill in when the deadline is by...");
        }

        this.description = eventlst[0];

        try {
            this.by = LocalDate.parse(eventlst[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please fill in the date in this format yyyy-mm-dd");
        }
    }

    /**
     * Creates a new DeadlineTask object to tasklist and prints message to user
     *
     * @return String to print out to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        DeadlineTask task = new DeadlineTask(this.description, this.by);
        tasks.add(task);
        storage.save(tasks);
        return super.getMessage(tasks, task);
    }
}
