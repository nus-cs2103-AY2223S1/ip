package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static ToDo fromString(String data) {
        char isDone = data.charAt(4);
        String description = data.substring(8);
        ToDo todo = new ToDo(description);
        if (isDone == 'X') {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toUser() {
        return "[T]" + super.toUser();
    }

}
