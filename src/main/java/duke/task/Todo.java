package duke.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public static Todo fromString(String inputString) {
        boolean isDone = inputString.charAt(4) == 'X';
        String name = inputString.substring(7);
        return new Todo(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}