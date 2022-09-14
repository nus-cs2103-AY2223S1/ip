package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    private String taskDescription;
    private LocalDate by;

    /**
     * Creates a deadline command.
     *
     * @param taskDescription description of task
     * @param by deadline of task
     * @throws DukeException if date has wrong format
     */
    public DeadlineCommand(String taskDescription, String by) throws DukeException {
        try {
            this.taskDescription = taskDescription;
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date/Time format is wrong!");
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful task addition message
     * @throws DukeException if date/time format is incorrect
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        Deadline newTask = new Deadline(taskDescription, by);
        return taskList.addTask(newTask);
    }
}
