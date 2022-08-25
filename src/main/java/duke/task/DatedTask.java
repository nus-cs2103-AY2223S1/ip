package duke.task;

import duke.task.Task;

import java.time.LocalDate;

public class DatedTask extends Task {
    protected LocalDate date;

    protected DatedTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }
    /**
     * Returns the date of dated task.
     *
     * @return a date
     */
    public LocalDate getDate() {
        return date;
    }
}
