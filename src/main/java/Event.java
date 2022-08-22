public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    @Override
    public String toDataEntry() {
        int mark = this.isDone ? 1 : 0;
        return String.format("E # %d # %s # %s\n", mark, this.description, this.at);
    }
}
