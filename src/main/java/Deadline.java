
public class Deadline extends Task {

    private static final String DEADLINE_LETTER = "D";

    // Deadline is of the form by: dateAndTime
    protected final String dateAndTime;

    // Constructors
    public Deadline(String description, String dateAndTime) {
        this(description, false, dateAndTime);
    }

    public Deadline(String description, boolean isDone, String dateAndTime) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }


    @Override
    public Deadline markAsDone() {
        return new Deadline(description, true, dateAndTime);
    }

    @Override
    public Deadline markAsUndone() {
        return new Deadline(description, false, dateAndTime);
    }
    

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", DEADLINE_LETTER, super.toString(), dateAndTime);
    }

}
