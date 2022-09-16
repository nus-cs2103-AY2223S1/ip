package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DateDukeException;
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
     * Creates a DeadlineCommand which includes description of task and when
     * the task needed to be completed by
     *
     * @param description Description of task
     * @throws DukeException either due to missing by field or poor date formatting
     */
    public DeadlineTaskCommand(String description) throws DukeException {
        super(description);
        assert description.split(" ")[0].equals("deadline") : "Keyword should be delete for DeleteCommand";

        String[] deadlineList = this.description.split("/by ", 2);

        if (deadlineList.length < 2 || deadlineList[1].equals("")) {
            throw new DukeException("Alamak! Fill in when the deadline is by...");
        }
        this.description = deadlineList[0];
        try {
            this.by = LocalDate.parse(deadlineList[1]);
        } catch (DateTimeParseException e) {
            throw new DateDukeException();
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
