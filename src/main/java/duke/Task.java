package duke;

import java.util.ArrayList;

/**
 * The task class.
 */
public class Task {
    private ArrayList<String> tags = new ArrayList<>();

    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task Object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the right icon.
     *
     * @return The string icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Formats the task into the correct String format.
     *
     * @return String in the right format.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.getDescription();
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The name of the tag.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     *  Prints the tag array.
     *
     * @return The String of the tag array
     */
    public String tagsToString() {
        if (tags.isEmpty()) {
            return "";
        } else {
            return " " + tags;
        }
    }

    /**
     *  Formats the tags for file storing
     *
     * @return the formatted string for tag storing
     */
    public String formatTagsForFile() {
        String temp = "";
        for (int i = 0; i < tags.size(); i++) {
            temp += " | " + tags.get(i);
        }
        return temp;
    }
}
