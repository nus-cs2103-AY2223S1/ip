package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() { isDone = true; }

    public void markAsUndone() { isDone = false; }

    public static Task fromString(String data) {
        if (data.charAt(1) == 'T') {
            return ToDo.fromString(data);
        }
        switch (data.charAt(1)) {
            case 'T':
                return ToDo.fromString(data);
            case 'D':
                return Deadline.fromString(data);
        }
        return new Task("fromStringExample");
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String toUser() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
