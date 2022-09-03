package Duke.Tasks;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime ddl;

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.ddl = LocalDateTime.of(date, LocalTime.parse("00:00"));
    }

    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        super(description, isDone);
        this.ddl = LocalDateTime.of(date, time);
    }

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
                this.discription,
                this.ddl.toString());
    }

    @Override
    public String getTaskType() { return "Deadline"; }

    @Override
    public LocalDateTime getDateTime() { return this.ddl; }


}
