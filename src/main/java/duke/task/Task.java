package duke.task;

import java.time.LocalDate;

public abstract class Task {

    protected String description;
    protected boolean isDone;

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

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String save();

    public static Task load(String task) {
        String[] split = task.split(" \\| ", 4);
        String taskType = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        LocalDate time = split.length == 4 ? LocalDate.parse(split[3]) : null;

        if (taskType.equals("T")) {
            return new ToDo(description, isDone);
        } else if (taskType.equals("E")) {
            return new Event(description, time, isDone);
        } else {
            return new Deadline(description, time, isDone);
        }
    }

    public abstract LocalDate getTime();
}
