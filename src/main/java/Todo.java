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
}
