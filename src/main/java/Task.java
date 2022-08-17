import java.time.LocalDate;

public abstract class Task {
    private String description;
    private boolean isDone;

    public static Task parse(String str) {
        String[] entry = str.split(" \\| ");
        Task task = null;
        if (entry[0].equals("T")) {
            task = new ToDo(entry[2]);
        } else if (entry[0].equals("D")) {
            task = new Deadline(entry[2], LocalDate.parse(entry[3]));
        } else {
            task = new Event(entry[2], LocalDate.parse(entry[3]));
        }
        if (entry[1].equals("1")) {
            task.mark();
        }
        return task;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String stringify() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
