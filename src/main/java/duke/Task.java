package duke;

import java.time.LocalDateTime;

public class Task {

    protected static final String defaultTagString = "DEFAULT_TAG_THAT_NO_ONE_WOULD_THINK_OF";
    protected String description;
    protected boolean isDone;
    protected String tag = defaultTagString;

    /**
     * Create a task class.
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String newTag) {
        this.tag = newTag;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.of(0, 1, 1, 0, 0);
    }

    public String getWriteString() {
        String marked = this.isDone() ? "1" : "0";
        return String.format("%s | %s | %s", marked, this.description, this.tag);
    }

    @Override
    public String toString() {
        String message = String.format("[%s] %s", this.getStatusIcon(), this.description);;
        if (!this.tag.equals(Task.defaultTagString)) {
            message = String.format("%s | Tag: %s", message, this.tag);
        }
        return message;
    }
}