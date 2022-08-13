public class Event extends Task{

    private String startTime;
    private String endTime;

    public Event(String name, boolean done, String startTime, String endTime) throws TaskNoNameException {
        super(name, done);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + this.startTime + " to " + this.endTime + ")";
    }

}