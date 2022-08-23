public class Event extends Task{

    protected String dateAndTime;

    public Event(String name, String dateAndTime) {
        super(name);
        this.dateAndTime = dateAndTime;
    }

    public Event(String name, String dateAndTime, boolean isDone) {
        super(name, isDone);
        this.dateAndTime = dateAndTime;
    }

    public String tag() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)",tag(), super.toString(), this.dateAndTime);
    }

    @Override
    public String savedString() {
        return String.format("%s,%s,%s", tag(), super.savedString(), this.dateAndTime);
    }
}
