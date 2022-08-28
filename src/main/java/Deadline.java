public class Deadline extends Task{

    protected String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by:%s)", super.toString(), this.dateTime);
    }
}
