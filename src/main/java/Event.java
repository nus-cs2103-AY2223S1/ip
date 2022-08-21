public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.numberOfTasks += 1;
    }
    public String getSaveData() {
        return "E|" + (super.isDone ? 1 : 0) + "|" + super.description + "|" + this.at;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + "(at: " + this.at + ")");
    }
}
