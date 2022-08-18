public class Event extends Task{
    protected String at;
    public Event(String taskName,String at) {
        super(taskName);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.taskName + " (at: " + this.at + ")";
    }
}
