package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDate atDate;
    private LocalDateTime atDateTime;
    private boolean hasTime;

    public Event(String desc, LocalDate atDate) {
        super(desc);
        this.atDate = atDate;
        this.isDone = false;
        this.hasTime = false;
    }

    public Event(String desc, LocalDateTime atDateTime) {
        super(desc);
        this.atDateTime = atDateTime;
        this.isDone = false;
        this.hasTime = true;
    }

    public Event(String desc, LocalDate atDate, boolean isDone) {
        super(desc, isDone);
        this.atDate = atDate;
        this.hasTime = false;
    }

    public Event(String desc, LocalDateTime atDateTime, boolean isDone) {
        super(desc, isDone);
        this.atDateTime = atDateTime;
        this.hasTime = true;
    }

    private String formatAt() {
        if (this.hasTime) {
            return this.atDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
        } else {
            return this.atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
    }
    
    private String saveFormatAt() {
        if (this.hasTime) {
            return this.atDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return this.atDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    @Override
    public String getSaveFormat() {
        return "E " + super.getSaveFormat() + " | " + this.saveFormatAt();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatAt() + ")";
    }
}
