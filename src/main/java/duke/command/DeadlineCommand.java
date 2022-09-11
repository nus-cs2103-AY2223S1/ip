package duke.command;

import java.time.LocalDate;

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

    /** Description of the deadline. */
    private String description;

    /** Date the deadline is due. */
    private LocalDate date;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the deadline.
     * @param date the date the deadline is due.
     */
    public DeadlineCommand(TaskList taskList, String description, LocalDate date) {
        this.taskList = taskList;
//        assert description.length() > 0;
        this.description = description;
        this.date = date;
    }

    @Override
    public String get() {
        taskList.add(new Deadline(description, date));
        return "Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}