package duke.command;

import duke.task.Deadline;
import duke.exception.IllegalDescriptionException;
import duke.logic.TaskList;

import java.time.LocalDate;

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

    /** Time the deadline is due. */
    private LocalDate time;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the deadline.
     * @param time the time the deadline is due.
     * @throws IllegalDescriptionException If no description is specified, including just whitespaces.
     */
    public DeadlineCommand(TaskList taskList, String description, LocalDate time) throws IllegalDescriptionException {
        this.taskList = taskList;
        //double check
        if (description.length() > 0) {
            this.description = description;
        } else {
            throw new IllegalDescriptionException("No description specified.");
        }
        this.time = time;
    }

    @Override
    public String get() {
        taskList.add(new Deadline(description, time));
        return "Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}