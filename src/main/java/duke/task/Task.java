package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public static Task parseTask(String str) {
        String[] entry = str.split("/");

        if (entry[0].equals("T")) {
            return new Deadline.ToDo(entry[2], Boolean.parseBoolean(entry[1]));

        } else if (entry[0].equals("E")) {
            LocalDate d = LocalDate.parse(entry[3]);
            return new Event(entry[2], Boolean.parseBoolean(entry[1]), d);

        } else {
            LocalDate d = LocalDate.parse(entry[3]);
            return new Deadline(entry[2], Boolean.parseBoolean(entry[1]), d);
        }
    }
    public boolean match(String toFind) {
        return description.contains(toFind);
    }
    public String formatTask() {
        return "task";
    }
    @Override
    public String toString() {
        return "task";
    }
}
