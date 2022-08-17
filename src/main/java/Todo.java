public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[T][" + symbol + "] " + this.description + "\n";
    }
}
