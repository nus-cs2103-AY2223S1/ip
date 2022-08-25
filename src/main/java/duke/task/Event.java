package duke.task;

import duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String dateTime;

    public Event(String item, String dateTime) {
        this.setItem(item);
        this.dateTime = dateTime;
    }

    public String getTask() {
        return "[E] " + this.getStatusIcon() + " " + this.getItem() + " (at: " + this.dateTime + ")";
    }

    public String getFileLine() {
        return "[E]" + "##" + this.getStatusIcon() + "##" + this.getItem() + "##" + this.dateTime;
    }
}