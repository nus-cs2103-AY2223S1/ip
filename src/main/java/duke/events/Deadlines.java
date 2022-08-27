package duke.events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{

    private String taskName;
    private LocalDateTime date;

    public Deadlines(String taskName, LocalDateTime date) {
        super(taskName);
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by:" + date.format(DateTimeFormatter.ofPattern(" hh:mm a 'on' dd/MM/yyyy")) + ")";
    }

    @Override
    public String getSaveData() {
        return " D" + " | " + super.isDone() + " | " + taskName + " | " + date;
    }
}
