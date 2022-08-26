package Duke;
public class Todo extends Task {

    // constructor
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
