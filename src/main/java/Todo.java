public class Todo extends Task {

    // constructor
    public Todo(String description) {
        super(description);
    }

    public String getTodoDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
