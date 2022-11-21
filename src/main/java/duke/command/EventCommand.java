package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.main.DukeException;
import duke.main.TaskList;
import duke.task.Event;


/**
 * A class that handles the event command.
 */
public class EventCommand extends AddCommand {
    private String description;
    private String date;

    /**
     * Constructs the event command.
     *
     * @param description a string that describes the task.
     * @param date the date on which the event is held.
     */
    public EventCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Adds new tasks.
     *
     * @param taskList the list of tasks.
     * @throws DukeException If cannot be added.
     * @throws DukeException if command cannot be executed.
     */
    @Override
    public void add(TaskList taskList) throws DukeException {
        try {
            taskList.add(new Event(description, LocalDate.parse(this.date)));
        } catch (DateTimeException e) {
            UndoCommand.deleteLast();
            throw new DukeException("Please give a valid date in YYYY-MM-DD format!");
        }
    }
}
