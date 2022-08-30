package bro.task;

import bro.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime byStore;
    protected String by;
    private boolean isMonthFormat;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        isMonthFormat = false;
        if (by.trim().split(" ").length == 4) {
            isMonthFormat = true;
        } else {
            byStore = Parser.deadlineParser(by);
        }
    }
    @Override
    public String getTaskType() {
        return "bro.task.Deadline";
    }

    @Override
    public String toString() {
        if(isMonthFormat){
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    byStore.format(DateTimeFormatter.ofPattern("MMM dd yyyy kkmm")) + ")";
        }
    }
}

