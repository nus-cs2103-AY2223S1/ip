package duke.command;

import duke.main.DukeException;
import duke.task.Event;
import duke.main.TaskList;
import java.time.DateTimeException;
import java.time.LocalDate;

public class EventCommand extends AddCommand {
    private String description;
    private String date;
    public EventCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public void add(TaskList taskList) throws DukeException {
        try {
            taskList.add(new Event(description, LocalDate.parse(this.date)));
        } catch(DateTimeException e) {
            throw new DukeException("Please give a valid date in YYYY-MM-DD format!");
        }
    }
}
