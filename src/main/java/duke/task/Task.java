package duke.task;

/**
 * A Task
 * @author Nephelite
 * @version 0.1
 */
public abstract class Task {
    private final String task;
    private boolean isDone;

    /**
     * Constructor for a Task
     * @param task the task
     * @param prefix the type of Event
     * @since 0.1
     */
    public Task(String task, String prefix) {
        String[] returnedArray = task.split(" ");
        String removedPrefix = "";
        int start = 0;
        if (returnedArray[0].equals(prefix)) {
            start = 1;
        }
        for (int i = start; i < returnedArray.length - 1; i++) {
            removedPrefix += returnedArray[i] + " ";
        }
        removedPrefix += returnedArray[returnedArray.length - 1];

        this.task = removedPrefix;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String box = isDone ? "[X] " : "[ ] ";
        return box + task;
    }
}
