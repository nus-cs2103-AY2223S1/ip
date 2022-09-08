public class Deadline extends Task{

    protected String dateTime;

    public Deadline(String description, String dateTime) throws MissingArgumentException{
        super("deadline", description, dateTime);
        if (dateTime.equals("")) {
            throw new MissingArgumentException("ERROR: deadline command is missing arguments.");
        }
        this.dateTime = dateTime;
    }

    public Deadline(String description, String dateTime, boolean isDone) throws MissingArgumentException{
        super("deadline", description, dateTime, isDone);
        if (dateTime.equals("")) {
            throw new MissingArgumentException("ERROR: deadline command is missing arguments.");
        }
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)", super.toString(), this.dateTime);
    }
}
