package duke.task;

import java.io.File;
import java.time.format.FormatStyle;

/**
 * An object representing a task in a checklist. This is the application's primary class.
 */
public class Task {
    public static final FormatStyle DATE_FORMAT = FormatStyle.MEDIUM;
    private static final String DATA_PATH = new File("").getAbsolutePath() + "/data/duke.txt";
    private String title;
    private boolean completed;

    Task(String title) {
        this.title = title;
        this.completed = false;
    }

    Task(String title, boolean completed) {
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
        System.out.println(String.format("Nice! I've marked this task as done:\n\t%s", this));
    }

    public void markAsIncomplete() {
        this.completed = false;
        System.out.println(String.format("OK, I've marked this task as not done yet:\n\t%s", this));
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
