package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * A Task object that the user inputs into the Chatbot. The Task contains a description of it and could be either
 * {@code markAsDone()} to indicate it is completed or {@code unmarkAsNotDone()} to indicate it is not completed.
 */
public class Task {
    /** Description of the task */
    protected String description;
    /** Completion status of the task */
    protected boolean isDone;
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    /**
     * Creates a Task object.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns {@code true} if the task instance is done, otherwise {@code false}.
     *
     * @return {@code true} if the task is completed, otherwise {@code false}.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns a string representation on the completion status of the task, "X" for done, otherwise an empty string.
     *
     * @return a string representation on whether the task is done
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the description of the given task object.
     *
     * @return a string representation of the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the given task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the given task as not done.
     */
    public void unmarkAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a non-empty string representation of the given task instance.
     *
     * @return the string representation of the task with its status
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + getDescription();
    }

}