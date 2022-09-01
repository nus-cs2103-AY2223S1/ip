package duke.task;

import duke.DukeException;

import java.util.Arrays;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createTodoFromString(String line) {
        return new ToDo(line.substring(10));
    }

    public boolean isOnThisDate(String dateStr) {
        return false;
    }

    public boolean doesDescriptionContain(String input) throws DukeException {
        return Arrays.asList(description.split(" ")).contains(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
