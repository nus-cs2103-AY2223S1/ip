public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String completionString = this.isDone ? "[T][x]" : "[T][ ]";
        return completionString + " " + this.description;
    }
}
