public class Todo extends Task{

    public Todo(String description) {
        super(description, "T");
    }

    public Todo(String description, String done) {
        super(description, done, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
