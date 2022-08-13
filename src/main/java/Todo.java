public class Todo extends Task {

    // class variables
    protected String by;

    // constructor
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
