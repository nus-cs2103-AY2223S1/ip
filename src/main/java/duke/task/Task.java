package duke.task;

import java.io.File;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

/**
 * An object representing a task in a checklist. This is the application's primary class.
 */
public class Task {
    public static final FormatStyle DATE_FORMAT = FormatStyle.MEDIUM;
    private static final String DATA_PATH = new File("").getAbsolutePath() + "/data/duke.txt";
    private String title;
    private boolean completed;

    private List<String> tags = new ArrayList<>();

    Task(String title) {
        assert !title.isBlank() : "Task title cannot be empty";

        this.title = title;
        this.completed = false;
    }

    Task(String title, boolean completed) {
        assert !title.isBlank() : "Task title cannot be empty";

        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }

    public List<String> getTags() {
        return tags;
    }

    public final void addTag(String tag) {
        this.tags.add(tag);
    }

    private String getStatusIcon() {
        return (this.completed ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.title);
    }

    public String toSaveString() {
        return String.format("%d,%s", this.completed ? 1 : 0, this.title);
    }
}
