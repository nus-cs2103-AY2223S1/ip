package duke.task;

/**
 * A task in the todo list.
 */
public class Task {
    private String content;
    private boolean done;
    private Tags tags;

    /**
     * Constructor for <code>Task</code>.
     * @param content
     */
    public Task(String content) {
        this.content = content;
        this.tags = new Tags();
        this.done = false;
    }

    public Task(String content, String[] tagsList) {
        this.content = content;
        this.tags = new Tags();
        for (int i = 0; i < tagsList.length; i++) {
            tags.addTags(new Tag(tagsList[i].trim()));
        }
        this.done = false;
    }

    /**
     * Mark a task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Unmark a marked task.
     */
    public void unmarkDone() {
        this.done = false;
    }

    /**
     * Get the description of a task.
     * @return
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Check if a task description contains given keyword.
     * @param keyword
     * @return boolean value
     */
    public boolean match(String keyword) {
        return this.content.contains(keyword);
    }

    /**
     * Return 1 if the task is done, 0 otherwise.
     * @return
     */
    public String isDoneString() {
        return this.done == true ? "1" : "0";
    }

    public String tagsString() {
        return tags.toString();
    }

    /**
     * String representative of a Task.
     * @return
     */
    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.content + " " + tagsString();
    }

    /**
     * Convert a task to a String to store with Storage.
     * @return
     */
    public String toMemoryString() {
        return isDoneString() + " | " + content + " | " + toMemoryTagString();
    }

    public String toMemoryTagString() {
        return tags.toMemoryString();
    }
}
