public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringForFile() {
        return super.toStringForFile() + "todo " + this.description;
    }

}
