package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Task {

    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags = new ArrayList<>();

    public static final String TIME_INPUT_PATTERN = "dd-MM-yyyy HHmm";
    public static final String DEADLINE_TIME_INPUT_PATTERN = "dd-MM-yyyy";

    /**
     * Create a task class.
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✔" : " "); // mark done task with ✔
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void addTag(String newTag) {
        this.tags.add(newTag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public boolean isTagless() {
        return this.tags.size() == 0;
    }

    public boolean containsTag(String tag) {
        return this.tags.contains(tag);
    }

    public void setTag(String tags) {
        if (tags.length() > 2) { // tags is nonempty
            String[] tagsList = tags.substring(1, tags.length() - 1).split(", ");
            this.tags = new ArrayList<>(Arrays.asList(tagsList));
        }
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.of(0, 1, 1, 0, 0);
    }

    public String getWriteString() {
        String marked = this.isDone() ? "1" : "0";
        return String.format("%s | %s | %s", marked, this.description, this.tags);
    }

    @Override
    public String toString() {
        String message = String.format("[%s] %s", this.getStatusIcon(), this.description);;
        if (!this.isTagless()) {
            message = String.format("%s\nTag: %s", message, this.tags);
        }
        return message;
    }
}