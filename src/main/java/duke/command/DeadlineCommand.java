package duke.command;

import duke.task.Deadline;
import duke.main.DukeException;
import duke.main.TaskList;
import java.time.DateTimeException;
import java.time.LocalDate;

public class DeadlineCommand extends AddCommand {
    private String description;
    private String date;
    public DeadlineCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Add new tasks.
     *
     * @param taskList the list of tasks
     */
    @Override
    public void add(TaskList taskList) throws DukeException {
        try {
            taskList.add(new Deadline(description, LocalDate.parse(date)));
        } catch(DateTimeException e) {
            throw new DukeException("Please give a valid date in YYYY-MM-DD format!");
        }
    }
}
