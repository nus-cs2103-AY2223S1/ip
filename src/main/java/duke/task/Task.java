package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, dd LLL yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
            "EEE, dd LLL yyyy, hh:mma"
    );
    private String description;
    private boolean isDone;

    public enum TaskType {
            TODO, DEADLINE, EVENT
    }

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String fileDescription() {
        if (isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    public boolean happensOn(LocalDate searchDate) {
        return false;
    }

    @Override
    public String toString() {
        String done;
        if (isDone) {
            done = "[X]";
        } else {
            done = "[ ]";
        }
        return done + " " + this.description;
    }

}
