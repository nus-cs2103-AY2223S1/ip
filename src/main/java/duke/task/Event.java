package duke.task;

import duke.Parser;
import duke.exception.ReadAttributeException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event extends Task {
    protected static final String SYMBOL = "E";
    private LocalDateTime time;

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public static Event parseEvent(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 4) {
            throw new ReadAttributeException("Event", formattedString, "Number of attributes less than 4");
        }
        Event result = event(attributes.get(2), Parser.parseStringToDateTime(attributes.get(3)));
        if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + this.SYMBOL + "]" + super.toString() + " (at: " + Parser.parseDateTimeToString(this.time) + ")";
    }

    @Override
    public String toFormattedString() {
        return Parser.combineAttributes(this.SYMBOL,
                Integer.toString(convertBoolToInt(this.getIsDone())),
                this.getName(),
                Parser.parseDateTimeToString(this.getTime()));
    }
}
