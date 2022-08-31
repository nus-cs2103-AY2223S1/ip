public class Event extends Task{
    protected String at;
    public Event(String taskName,boolean isDone, String at) {
        super(taskName,isDone);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.taskName + " (at: " + this.at + ")";
    }

    @Override
    public String toStore() {
        return "|E|" + "|" + this.getStatusIcon() + "| " + this.taskName + " (at: " + this.at + ")";
    }
}
