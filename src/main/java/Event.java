import java.time.format.DateTimeParseException;

public class Event extends Item{
    public Event(String name, String eventTime) throws DateTimeParseException {
        super(name, ItemTypes.EVENT, eventTime);
    }

    public Event(String name, String eventTime, boolean isDone) throws DateTimeParseException {
        super(name, isDone, ItemTypes.EVENT, eventTime);
    }

    @Override
    public String toString() {
        return this.getItemType() + super.toString() + " (at: " + this.getDateTimeString() + ")";
    }
}
