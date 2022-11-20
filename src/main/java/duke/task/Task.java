package duke.task;

import java.util.ArrayList;

/**
 * A Task
 *
 * @author Nephelite
 * @version 0.3
 */
public abstract class Task {
    private final String task;
    private boolean isDone;
    private ArrayList<String> tags;

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
        tags = new ArrayList<>();
    }

    /**
     * Sets isDone to true
     * @since 0.1
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Sets isDone to false
     * @since 0.1
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Checks if the task or tags contain the given word
     *
     * @param word the word to check
     * @return true if the word is contained in the task or tags. Otherwise, false
     */
    public boolean isTaskAndWordMatch(String word) {
        assert(word != null);
        String[] wordsToCheck = task.split(" ");
        for (int i = 0; i < wordsToCheck.length; i++) {
            if (wordsToCheck[i].equals(word)) {
                return true;
            }
        }
        for (int i = 0; i < tags.size(); i++) {
            return tags.contains(word);
        }
        return false;
    }

    /**
     * Adds a tag to tags
     *
     * @param tag
     * @since 0.3
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Gets all tags in a list
     *
     * @return all tags as a single line, with all tags having a # prefix.
     * @since 0.3
     */
    public String getTags() {
        String response = "";
        for (int i = 0; i < tags.size(); i++) {
            response += " #" + tags.get(i);
        }
        return response;
    }

    /**
     * Returns a String representation of a Task
     *
     * @return String representation of a Task
     * @since 0.3
     */
    @Override
    public String toString() {
        String box = isDone ? "[X] " : "[ ] ";
        return box + task;
    }
}
