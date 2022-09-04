import java.time.format.DateTimeParseException;

public class Deadline extends Item{
    public Deadline(String name, String dueDate) throws DateTimeParseException {
        super(name, ItemTypes.DEADLINE, dueDate);
    }


    public Deadline(String name, String dueDate, boolean isDone) throws DateTimeParseException {
        super(name, isDone, ItemTypes.DEADLINE, dueDate);
    }

    @Override
    public String toString() {
        return this.getItemType() + super.toString() + " (by: " + this.getDateTimeString() + ")";
    }
}
