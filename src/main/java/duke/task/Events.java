package duke.task;

public class Events extends Task {
    private String specificTime;

    public Events(String description, String specificTime) {
        super(description);
        this.specificTime = specificTime;
    }

    public String storedTaskString() {
        return "E|" + String.valueOf(this.isDone) + "|" + this.description + "|" + this.specificTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.specificTime + ")";
    }
}
