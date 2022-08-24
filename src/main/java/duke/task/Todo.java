package duke.task;

import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String description) {
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
    public boolean isHappeningOnDate(LocalDate localDate) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
