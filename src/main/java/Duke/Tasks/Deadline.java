package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that extends from Task with additional variable LocalDataTime
 * and represents as Deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime ddl;

    /**
     * Public Constructor of Deadline class.
     *
     * @param description The description of the Deadline task.
     * @param date The date of the deadline.
     * @param isDone The status of the deadline
     */
    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.ddl = LocalDateTime.of(date, LocalTime.parse("00:00"));
    }

    /**
     * Public Constructor of Deadline class.
     *
     * @param description The description of the Deadline task.
     * @param date The date of the deadline.
     * @param time The time of the deadline on that date.
     * @param isDone The status of the deadline
     */
    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        super(description, isDone);
        this.ddl = LocalDateTime.of(date, time);
    }

    /**
     * Public Constructor of Deadline class.
     *
     * @param description The description of the Deadline task.
     * @param ddl The date and the time of the deadline.
     * @param isDone The status of the deadline
     */
    public Deadline(String description, LocalDateTime ddl, boolean isDone) {
        super(description, isDone);
        this.ddl = ddl;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (%s)", super.toString(),
                this.ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
    @Override
    public String save() {
        return String.format("D | %b | %s | %s\n",
                super.getIsDone(),
                this.description,
                this.ddl.toString());
    }
    @Override
    public String getTaskType() { return "Deadline"; }
    @Override
    public LocalDateTime getDateTime() { return this.ddl; }


}
