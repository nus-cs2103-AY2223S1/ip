public class Event extends Task {
    protected String eventTime;

    public Event(String task, String eventTime) {
        super(task);
        this.eventTime = eventTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof Event)) {
            return false;
        }
        Event temp = (Event) obj;
        if (super.equals(temp) && this.eventTime.equals(temp.eventTime)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() { 
        return String.format("[E]%s (at: %s)", super.toString(), this.eventTime);
    }
}