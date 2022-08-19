public class event extends Task {
    public String eventTime;

    public event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + eventTime + ")";
    }
}
