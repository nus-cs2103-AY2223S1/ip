public class Todo extends Task {

    public Todo(String description) {
        super(description.substring(5));
    }

    @Override
    public String fileFormat() {
        return String.format("todo | %s | %b", super.description, super.isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
