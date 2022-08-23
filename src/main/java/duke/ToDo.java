package duke;

public class ToDo extends Task {
    public ToDo(String isDone, String description) {
        super(description, isDone.equals("1"));
    }

    @Override
    public String toStringSaveFormat() {
        return String.format("T,%s,%s\n", this.isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
