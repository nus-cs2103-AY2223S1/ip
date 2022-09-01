package pluto.task;

import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String color = (isDone ? Task.ANSI_GREEN : Task.ANSI_RED);
        return color + "[T]" + super.toString() + Task.ANSI_RESET;
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        return String.format("T | %d | %s", done, description);
    }

    public LocalDate getDateMaybe() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo other = (Todo) o;
            return this.description.equals(other.description) && this.isDone == other.isDone;
        }
        return false;
    }
}
