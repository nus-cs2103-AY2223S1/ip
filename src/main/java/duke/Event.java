package duke;

import java.time.LocalDate;

public class Event extends Task{
    private final LocalDate date;

    public Event(String name, String dateString) {
        super(name);
        this.date = Parser.convertStringToDate(dateString);
    }


    @Override
    public String toString() {
        String tag = "[E]";
        return tag + "[" + this.getStatusIcon()  + "] " + this.getTaskName() + "(" + Parser.convertDateToString(date) + ")";
    }
}
