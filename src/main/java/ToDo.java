public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String getSaveString() {
        return "T | " + (isDone ? "1 | " : "0 | " + this.description);
    }
}
