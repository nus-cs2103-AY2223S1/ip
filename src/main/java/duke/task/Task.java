package duke.task;

/**
 * A task in the todo list.
 */
public class Task {
    private String content;
    private Tag tag;
    private boolean done;

    /**
     * Constructor for <code>Task</code>.
     *
     * @param content
     */
    public Task(String content) {
        this.content = content;
        this.tag = new Tag();
        this.done = false;
    }

    public Task(String content, String tag) {
        this.content = content;
        this.tag = new Tag(tag);
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
     * Check if a task description contains given keyword.
     *
     * @param keyword
     * @return boolean value
     */
    public boolean match(String keyword) {
        return this.content.contains(keyword);
    }

    public boolean isTagged() {
        return !tag.isEmpty();
    }

    /**
     * Return 1 if the task is done, 0 otherwise.
     *
     * @return
     */
    public String isDoneString() {
        return this.done == true ? "1" : "0";
    }

    public String tagsString() {
        return tag.toString();
    }

    /**
     * String representative of a Task.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.content + " " + tagsString();
    }

    /**
     * Convert a task to a String to store with Storage.
     *
     * @return
     */
    public String toMemoryString() {
        return " | " + isDoneString() + " | " + content + " | " + tag.toMemoryString() + " | ";
    }
}
