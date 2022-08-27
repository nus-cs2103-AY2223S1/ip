package task;

public class ToDo extends Task {
    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toFileData() {
        return String.format("T | %d | %s", this.status ? 1 : 0, this.content);
    }
}
