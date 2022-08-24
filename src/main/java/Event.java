public class Event extends ListObject{

    String eventTime;

    public Event(String task, int status) {
        super(task, status);
    }

    public Event(String task, int status, String eventTime) {
        super(task, status);
        this.eventTime = eventTime;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
