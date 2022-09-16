package duke.task;

import duke.Parser;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDateException;

/**
 * An abstract class representing the Tasks included in Duke.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected Tag tag;

    /**
     * Constructs a Task.
     *
     * @param description Description of the task.
     * @param isDone Boolean value where true indicates the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tag = Tag.NO_TAG;
    }

    /**
     * Returns a String indicating whether the task is done.
     *
     * @return "X" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string format of this task including its index.
     *
     * @param index Index of this task in a list/array.
     * @return String representation of this task based on its index in a list/array.
     */
    public String toStringWithIndex(int index) {
        return index + "." + this.toString();
    }

    public static Task createTask(String commandType, String[] inputs) {
        Task task;
        try {
            switch(commandType) {
            case "TODO":
                task = new ToDo(Parser.getDescription(inputs, null), false);
                break;
            case "DEADLINE":
                task = new Deadline(Parser.getDescription(inputs, "/by"),
                        false,
                        Parser.getTime(inputs, "/by"));
                break;
            case "EVENT":
                task = new Event(Parser.getDescription(inputs, "/at"),
                        false,
                        Parser.getTime(inputs, "/at"));
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (NumberFormatException e) {
            throw new DukeInvalidDateException();
        }
        return task;
    }

    public void addTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * Returns the string format of this task to be saved in the save file.
     *
     * @return String representation of this task in the format it is saved in the save file.
     */
    public abstract String toFileFormat();

    /**
     * Returns a boolean indicating if this task is the same as the obj.
     *
     * @param obj Object to be compared to.
     * @return True if this task and obj are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task tmp = (Task) obj;
            return tmp.description.equals(this.description) &&
                    tmp.isDone == this.isDone;
        }
        return false;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representing this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}