package duke.task;

import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String status) {
        super(description);
        if (status.equals("1")) {
            super.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}