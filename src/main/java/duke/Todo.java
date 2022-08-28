package duke;

import java.time.LocalDate;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public void setTime(String time) {
        return;
    }

    public void setDate(LocalDate date) {
        return;
    }

    /**
     * Returns the tag for the task type.
     *
     * @return The 'T' tag for Todo task.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getTime() {
        return null;
    }

    public String getDateFormat() {
        return "";
    }

    /**
     * Returns string representation of the task.
     *
     * @return Todo task string representaion.
     */
    @Override
    public String toString() {
        return "[T] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName();
    }
}
