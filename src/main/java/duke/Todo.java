package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    // TodoClass strings look like: [T][X] read book
    public static Todo stringToTodo(String s) throws DukeException {
        if (!s.startsWith("[T][")) {
            throw new DukeException("This string is not a duke.Todo string!");
        }

        char isDoneString = s.charAt(4); //[T][X] checks if X is present
        char X = 'X';
        boolean isDone = isDoneString == X;

        String description = s.substring("[T][X] ".length());
        return new Todo(description, isDone);
    }

    public static void main(String[] args) throws DukeException {
        String testString = "[T][X] read book";
        System.out.println(stringToTodo(testString).toString().equals(testString));
    }
}
