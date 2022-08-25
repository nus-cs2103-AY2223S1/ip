package dukechatbot.utility;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.time = null;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String out = "[" + getStatusIcon() + "] " + this.description;
        return out;
    }
}
