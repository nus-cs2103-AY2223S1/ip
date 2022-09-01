package duke.task;

import java.io.File;

import java.time.format.FormatStyle;


public class Task {
    private static final String DATA_PATH = new File("").getAbsolutePath() + "/data/duke.txt";
    private String title;
    private boolean completed;

    public static final FormatStyle DATE_FORMAT = FormatStyle.MEDIUM;

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
    }

    public void markAsIncomplete() {
        this.completed = false;
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
