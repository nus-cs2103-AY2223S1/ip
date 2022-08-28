package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDate byDate;
    private LocalDateTime byDateTime;
    private boolean hasTime;

    public Deadline(String desc, LocalDate byDate) {
        super(desc);
        this.byDate = byDate;
        this.hasTime = false;
    }

    public Deadline(String desc, LocalDateTime byDateTime) {
        super(desc);
        this.byDateTime = byDateTime;
        this.hasTime = true;
    }

    public Deadline(String desc, LocalDate byDate, boolean isDone) {
        super(desc, isDone);
        this.byDate = byDate;
        this.hasTime = false;
    }

    public Deadline(String desc, LocalDateTime byDateTime, boolean isDone) {
        super(desc, isDone);
        this.byDateTime = byDateTime;
        this.hasTime = true;
    }
    
    private String formatBy() {
        if (this.hasTime) {
            String formattedDateTime = this.byDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
            return formattedDateTime;
        } else {
            String formattedDate = this.byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            return formattedDate;
        }
    }

    private String saveFormatBy() {
        if (this.hasTime) {
            return this.byDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return this.byDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    @Override
    public String getSaveFormat() {
        return "D " + super.getSaveFormat() + " | " + this.saveFormatBy();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatBy() + ")";
    }
}
