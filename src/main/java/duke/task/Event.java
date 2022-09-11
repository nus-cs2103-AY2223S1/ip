package duke.task;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an Event task with an event description, start date and end date
 * Inherits from Task class
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, Boolean isDone, LocalDate startDate, LocalDate endDate, List<String> tags) {
        super(description, isDone, tags);
        assert startDate.isBefore(endDate) || startDate.isEqual(endDate)
                : "invalid date range (start date after end date).";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][X]" : "[E][ ]"); // mark done task with X
    }

    @Override
    public String getDescription() {
        return String.format("%s (at %s - %s)", this.description, this.startDate, this.endDate);
    }

    public String toString() {
        String status = isDone ? "Done  " : "UnDone";
        return String.format("Event     | %s | %s | %s to %s | %s", status, super.getDescription(),
                this.startDate, this.endDate, super.printTags());
    }

    @Override
    public String taskType() {
        return "event";
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
