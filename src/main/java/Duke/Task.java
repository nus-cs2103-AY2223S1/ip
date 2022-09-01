package duke;

import java.time.LocalDateTime;

/**
 * Abstract class representing a task
 * Each sub-class of task will be a specific task type
 * @author Reuben Chay
 */
abstract public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks a class as done
     */
    void Done() {
        this.isDone = true;
    }

    /**
     * Marks a class as undone
     */
    void unDone() {
        this.isDone = false;
    }

    boolean getDone() {
        return this.isDone;
    }

    String getName() {
        return this.name;
    }

    abstract LocalDateTime getDateTime();
    abstract boolean isToDo();

    @Override
    public String toString() {
        String out = "";
        if (this.isDone) {
            out += "[X]";
        } else {
            out += "[ ]";
        }
        out += " " + this.name;
        return out;
    }
}
