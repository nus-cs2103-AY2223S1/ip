public class Event extends Task{
    private String dateAndTime;

    public Event(String name, String dateAndTime) {
        super(name);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.dateAndTime + ")";
    }
}
