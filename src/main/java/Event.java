public class Event extends Task{
    private String at;

    Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    String getBy() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("%s%s(at: %s)", "[E]", super.toString(), this.at);
    }
}
