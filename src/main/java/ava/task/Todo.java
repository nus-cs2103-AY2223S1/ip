package ava.task;

/**
 * Class to represent "Todo" tasks.
 */
public class Todo extends Task {
    /**
     * The constructor for Todo task.
     *
     * @param description Task description.
     * @param isDone Task state.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks done a Todo task.
     *
     * @return Todo object.
     */
    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    /**
     * Marks undone a Todo task.
     *
     * @return Todo object.
     */
    @Override
    public Todo markUndone() {
        super.markUndone();
        return this;
    }

    /**
     * Changes the format of Task to write to the file.
     *
     * @return String format to write to file.
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "T | " + mark + " | " + this.description;
    }

    /**
     * Obtains a string representation of the task.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Comparator method. DO NOT USE !!! Implemented to bypass abstract superclass implementing Comparable interface.
     *
     * @param otherTask The other task to be compared to.
     * @return 0.
     */
    @Override
    public int compareTo(Task otherTask) {
        return 0;
    }
}
