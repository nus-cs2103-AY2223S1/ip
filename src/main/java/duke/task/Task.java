package duke.task;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String mark() {
        this.isDone = true;
        return this.toString();
    }

    public String unmark() {
        this.isDone = false;
        return this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (this.isDone ? "X" : " "), this.description);
    }

    public static Task loadTask(String data) {
        String[] dataSplit = data.split(" \\| ", 4);
        char c = dataSplit[0].charAt(0);
        boolean isDone = dataSplit[1].equals("1");
        String description = dataSplit[2];
        LocalDate time = dataSplit.length == 4 ? LocalDate.parse(dataSplit[3]) : null;

        switch (c) {
        case 'D':
            return new Deadline(description, isDone, time);
        case 'E':
            return new Event(description, isDone, time);
        default:
            return new Todo(description, isDone);
        }
    }

    public String saveTask() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }
}