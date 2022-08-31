package duke.task;

/**
 * A Task
 *
 * @author Nephelite
 * @version 0.1
 */
public abstract class Task {
    private final String task;
    private boolean isDone;

    /**
     * Constructor for a Task
     *
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

    /**
     * Sets isDone to true
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Sets isDone to false
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Checks if the task contains the given word
     *
     * @param word the word to check
     * @return true if the word is contained in the task. Otherwise, false
     */
    public boolean isTaskAndWordMatch(String word) {
        String[] wordsToCheck = task.split(" ");
        for (int i = 0; i < wordsToCheck.length; i++) {
            if (wordsToCheck[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a String representation of a Task
     *
     * @return String representation of a Task
     */
    @Override
    public String toString() {
        String box = isDone ? "[X] " : "[ ] ";
        return box + task;
    }
}
