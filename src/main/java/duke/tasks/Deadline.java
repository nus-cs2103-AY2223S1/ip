package duke.tasks;

import duke.tools.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", super.getTaskIcon(), super.toString(),
                Parser.displayDateTime(this.dateTime));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline that = (Deadline) o;
            return this.getDescription().equals(that.getDescription())
                    && this.getDateTime().isEqual(that.getDateTime());
        }
        return false;
    }
}
