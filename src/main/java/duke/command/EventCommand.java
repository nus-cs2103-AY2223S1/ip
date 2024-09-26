package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a event command.
 */
public class EventCommand extends Command {
    private String taskDescription;
    private LocalDate at;

    /**
     * Creates an event command.
     *
     * @param taskDescription description of task
     * @param at date of task
     * @throws DukeException if date has wrong format
     */
    public EventCommand(String taskDescription, String at) throws DukeException {
        try {
            this.taskDescription = taskDescription;
            this.at = LocalDate.parse(at);
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
        Event newTask = new Event(taskDescription, at);
        return taskList.addTask(newTask);
    }
}
