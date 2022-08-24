public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    public String getName() {
        return "T";
    }

    public String timing() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
