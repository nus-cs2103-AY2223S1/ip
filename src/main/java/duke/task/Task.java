package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toDataFormat() {
        return "The data are as follows:";
    }

    public boolean isOn(LocalDate date) {
        return false;
    }

    /**
     * Checks if the description contains this keyword.
     *
     * @param string The keyword.
     * @return a boolean value.
     */
    public boolean contains(String string) {
        return description.contains(string);
    }

    @Override
    public String toString() {
        /** Determines if there is an X icon to show completed */
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, description);
    }
}
