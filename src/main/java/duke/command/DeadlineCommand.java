package duke.command;

import java.time.LocalDate;

import duke.exception.DuplicateTaskException;
import duke.logic.TaskList;
import duke.task.Deadline;

/**
 * DeadlineCommand is a command for Duke to remember a deadline.
 *
 * @author totsukatomofumi
 */
public class DeadlineCommand extends Command {
    /** Task list the command has to add a deadline to. */
    private TaskList taskList;

    private Deadline deadline;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the deadline.
     * @param date the date the deadline is due.
     * @throws DuplicateTaskException If the deadline specified already exists.
     */
    public DeadlineCommand(TaskList taskList, String description, LocalDate date) throws DuplicateTaskException {
        this.taskList = taskList;
        assert description.length() > 0;
        this.deadline = new Deadline(description, date);
        if (this.taskList.contains(this.deadline)) {
            throw new DuplicateTaskException("duplicate deadline already exists");
        }
    }

    @Override
    public String get() {
        taskList.add(this.deadline);
        return "Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}