public class Event extends Task {
    // original access modifier was protected
    private final String start;

    public Event(String description, Command taskCommand, String start) {
        super(description, taskCommand);
        this.start = start;
    }

    @Override
    public String getFileStorageString(int index) {
        return taskCommand.getString() + " " + description + " /at " + start + "\n" + getTaskDoneString(index);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + start + ")";
    }
}
