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

    @Override
    public void add(TaskList tasks) throws DukeException {
        try {
            tasks.add(new Deadline(description, LocalDate.parse(date)));
        } catch(DateTimeException e) {
            throw new DukeException("Please give a valid date in YYYY-MM-DD format!");
        }
    }
}
