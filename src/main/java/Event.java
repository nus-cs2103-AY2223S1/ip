public class Event extends Task {
    private String at;

    public Event(String description, boolean done, String at) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String getTime() {
        return this.at;
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[E][X]" : "[E][ ]";
        String dateFormatted = "(at: " + at + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
