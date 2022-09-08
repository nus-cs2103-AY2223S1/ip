import java.time.LocalDate;

public class Deadline extends Task{

    protected LocalDate dateTime;

    public Deadline(String description, String dateTime) throws MissingArgumentException, InvalidDateException{
        super("deadline", description, dateTime);
        if (dateTime.equals("")) {
            throw new MissingArgumentException("ERROR: deadline command is missing arguments.");
        }
        this.dateTime = super.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.format(super.outputDateFormatter));
    }
}
