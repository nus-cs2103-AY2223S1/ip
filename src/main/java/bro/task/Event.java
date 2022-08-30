package bro.task;

import bro.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime atStore;
    protected String at;
    protected boolean isMonthFormat;
    protected Parser parser;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        isMonthFormat = false;
        if(at.trim().split(" ").length == 4){
            isMonthFormat = true;
        } else {
           atStore = parser.eventParser(at);
        }
    }

    @Override
    public String getTaskType() {
        return "bro.task.Event";
    }

    @Override
    public String toString() {
        if(isMonthFormat){
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString() + " (at: " +
                    atStore.format(DateTimeFormatter.ofPattern("MMM dd yyyy hhmm")) + ")";
        }
    }
}
