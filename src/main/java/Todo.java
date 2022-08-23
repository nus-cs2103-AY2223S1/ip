public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    public String toFileDescription() {
        return "T" + " | " + super.toFileDescription();
    }

    public static Todo fromFileDescription(String input) {
        String[] strArray = input.split(" \\| ", 3);
        boolean isDone;
        Todo todo = new Todo(strArray[2]);
        if (strArray[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
