package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {

    LocalDate eventTime;
    Event (String name, String eventTime) {
        super(name);
        this.eventTime = LocalDate.parse(eventTime);
    }
    Event (String name, String eventTime, boolean done) {
        super(name, done);
        this.eventTime = LocalDate.parse(eventTime.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
        assert(this.eventTime.isAfter(LocalDate.now()));
    }

    @Override
    public String toString() {
        String out = "[E][";
        if (super.getStatus()) {
            out += "X";
        } else {
            out += " ";
        }
        out += "] " + super.toString() + "(at : " + eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return out;
    }
}
