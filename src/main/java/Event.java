import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {

    LocalDate eventTime;
    Event (int id, String name, String eventTime) {
        super(id, name);
        this.eventTime = LocalDate.parse(eventTime);
    }
    Event (int id, String name, String eventTime, boolean done) {
        super(id, name, done);
        this.eventTime = LocalDate.parse(eventTime);
    }

    @Override
    public String toString() {
        String out = super.getId() + ".[E][";
        if (super.getStatus())
            out += "X";
        else
            out += " ";
        out += "] " + super.toString() + "(at : " + eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return out;
    }
}
