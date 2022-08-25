public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toFileFormat() {
        String isDone = this.isDone ? "1" : "0";
        return "T | " + isDone + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
