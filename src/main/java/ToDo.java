public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createTodoFromString(String line) {
        return new ToDo(line.substring(10));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
