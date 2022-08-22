public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStringSaveFormat() {
        return String.format("T,%s,%s\n", isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
