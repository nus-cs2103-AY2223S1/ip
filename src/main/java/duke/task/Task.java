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
        isDone = true;
        return toString();
    }

    public String unmark() {
        isDone = false;
        return toString();
    }

    /**
     * Checks whether the description contains the given keyword.
     *
     * @param keyword Given keyword.
     * @return Whether the description contains the given keyword.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? "X" : " "), description);
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
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }
}
