public class Todo extends Task {

    public Todo(String command) {
        super(command);
    }

    @Override
    public String toString() {
        if (done) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }
    }

    @Override
    public String toStringText() {
        return "T | " + this.done + " | " + this.description;
    }
}
