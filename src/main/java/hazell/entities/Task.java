package hazell.entities;

import hazell.exceptions.TaskDescriptionEmpty;

/**
 * A superclass to be inherited by other Tasks.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Checks whether fields are valid. To be called when creating Task via factory methods.
     *
     * @throws TaskDescriptionEmpty If the user did not specify a description
     */
    protected void validate() throws TaskDescriptionEmpty {
        if (description.equals("")) {
            throw new TaskDescriptionEmpty();
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " "; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String serialise();

    /**
     * Unserialses data to create a Task (factory method).
     *
     * @param s Input string to parse data from
     * @return A subclass object
     */
    public static Task unserialise(String s) {
        String[] words = s.split(" \\| ");
        Task task;
        switch (words[0]) {
        case ("T"):
            task = ToDo.unserialise(words);
            break;
        case ("D"):
            task = Deadline.unserialise(words);
            break;
        case ("E"):
            task = Event.unserialise(words);
            break;
        default:
            task = null;
            break;
        }
        return task;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
