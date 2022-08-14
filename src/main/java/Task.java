/**
 * This class encapsulates a task set by the user.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    Task(String description) throws DwukeException {
        if (description.equals("")) {
            throw new DwukeException("oops!!! da descwiption of a twask cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Changes the description of this task to the new description.
     *
     * @throws DwukeException If the new description is empty.
     */
    public void changeDescription(String newDescription) throws DwukeException {
        if (newDescription.equals("")) {
            throw new DwukeException("oops!!! da descwiption of a twask cannot be empty.");
        }
        this.description = newDescription;
    }

    /**
     * Returns a String representation of this task.
     *
     * @return A String representing this task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.description;
    }
}
