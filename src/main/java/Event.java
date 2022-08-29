public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    @Override
    public String getTaskType() {
        return "E";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    String saveStringToFile() {
        return String.format("%s%s\n", super.saveStringToFile(), at);
    }
}
