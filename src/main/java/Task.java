import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String taskName;
    private final boolean markDone;
    private final LocalDate date;
    private final LocalTime time;

    Task(String taskName, LocalDate date, LocalTime time) {
        this.taskName = taskName;
        this.markDone = false;
        this.date = date;
        this.time = time;
    }

    Task(String taskName, boolean markDone, LocalDate date, LocalTime time) {
        this.taskName = taskName;
        this.markDone = markDone;
        this.date = date;
        this.time = time;
    }

    public Task mark() {
        return new Task(this.taskName, true, this.date, this.time);
    }

    public Task unmark() {
        return new Task(this.taskName, false, this.date, this.time);
    }

    public boolean isMarked() {
        return this.markDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDateAndTime() {
        if (this.time != null && this.date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return " " + this.date.format(formatter) + ", " + this.time.toString() + ")";
        } else {
            return "";
        }
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[" + (this.isMarked() ? "X" : " ") + "]" + this.taskName + this.getDateAndTime() + "\n";
    }
}
