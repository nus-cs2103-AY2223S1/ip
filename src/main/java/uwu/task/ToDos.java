package uwu.task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        String isDoneIndicator = super.isDone ? "1" : "0";

        return "T," + isDoneIndicator + "," + description.trim();
    }
}
