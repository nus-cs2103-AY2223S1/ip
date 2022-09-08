package duke.command;

import duke.main.DukeException;
import duke.main.TaskList;
import duke.task.Deadline;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * A class for deadline command.
 */
public class DeadlineCommand extends AddCommand {
    private String description;
    private String date;

    /**
     * Constructor for deadline command.
     *
     * @param description a string that describes the task.
     * @param date a date to indicate the dateline.
     */
    public DeadlineCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Add new tasks.
     *
     * @param taskList the list of tasks.
     */
    @Override
    public void add(TaskList taskList) throws DukeException {
        try {
            taskList.add(new Deadline(description, LocalDate.parse(date)));
        } catch(DateTimeException e) {
            UndoCommand.deleteLast();
            throw new DukeException("Please give a valid date in YYYY-MM-DD format!");
        }
    }
}
