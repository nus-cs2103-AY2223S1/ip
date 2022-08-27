package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, Boolean isDone, LocalDate startDate, LocalDate endDate) {
        super(description, isDone);
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
        return String.format("Event     | %s | %s | %s to %s", status, super.getDescription(),
                this.startDate, this.endDate);
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
