package duke.task;

import duke.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String item, LocalDate date) {
        this.setItem(item);
        this.date = date;
    }

    public String getTask() {
        String d = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D] " + this.getStatusIcon() + " " + this.getItem() + " (by: " + d + ")";
    }
    public String getFileLine(){
        String d = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + "##" + this.getStatusIcon() + "##" + this.getItem() + "##" + d;
    }
}