package duke.task;

import java.time.LocalDateTime;

/**
 * Encapsulates the user Task.
 * It stores a detail (String) of the user input (String after command of user input)
 * ,and it provides the skeletal implementation of all user task.
 * It also stores the status of done/undone of the task.
 * Every user task should be the child-class of this abstract class.
 */
public abstract class Task {
    private final String detail;
    private final boolean isDone;

    /**
     * Constructor of Task class.
     * Initialises isDone attribute as false. (Undone)
     *
     * @param detail String of detail extracted from user's raw input.
     */
    Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    /**
     * Constructs Task object.
     * Allows the changes of done/undone status of task.
     *
     * @param detail String of detail extracted from user's raw input.
     * @param isDone true/false of the task's done status.
     */
    Task(String detail, boolean isDone) {
        this.detail = detail;
        this.isDone = isDone;
    }
    public abstract Task markDone();

    public abstract Task unmarkDone();

    public abstract String getId();

    public abstract LocalDateTime getTime();

    public String getDetail() {
        return this.detail;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public boolean equals(Object otherTask) {
        if (otherTask instanceof Task) {
            return ((Task) otherTask).getDetail().equals(this.detail);
        }
        return false;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.detail;
    }
}
