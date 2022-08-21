package models;

import java.time.LocalDate;

/**
 * Encapsulates a task without any date/time attached to it, e.g. visit new theme park
 *
 * @author Emily Ong Hui Qi
 */

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return TaskType.TODO.toString();
    }

    @Override
    public LocalDate getDate() {
        return null;
    }
}
